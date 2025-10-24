package com.elplaneador.accounting.service;

import com.elplaneador.accounting.dto.AsientoDTO;
import com.elplaneador.accounting.dto.LibroDiarioDTO;
import com.elplaneador.accounting.entity.AsientoContable;
import com.elplaneador.accounting.entity.CatalogoCuentas;
import com.elplaneador.accounting.entity.LibroDiario;
import com.elplaneador.accounting.repository.CatalogoCuentasRepository;
import com.elplaneador.accounting.repository.LibroDiarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.util.List;

@Service
public class LibroDiarioService {
    
    @Autowired
    private LibroDiarioRepository libroDiarioRepository;
    
    @Autowired
    private CatalogoCuentasRepository catalogoCuentasRepository;
    
    @Transactional
    public LibroDiario registrarAsiento(LibroDiarioDTO dto) {
        // Validar partida doble
        BigDecimal totalDebe = BigDecimal.ZERO;
        BigDecimal totalHaber = BigDecimal.ZERO;
        
        for (AsientoDTO asiento : dto.getAsientos()) {
            totalDebe = totalDebe.add(asiento.getDebe());
            totalHaber = totalHaber.add(asiento.getHaber());
        }
        
        if (totalDebe.compareTo(totalHaber) != 0) {
            throw new RuntimeException("Error: El debe y el haber no coinciden. Debe: " + totalDebe + ", Haber: " + totalHaber);
        }
        
        // Crear el asiento en el libro diario
        LibroDiario libroDiario = new LibroDiario();
        libroDiario.setFecha(dto.getFecha());
        libroDiario.setDescripcion(dto.getDescripcion());
        libroDiario.setServerIp(getServerIp());
        
        // Agregar cada asiento contable
        for (AsientoDTO asientoDTO : dto.getAsientos()) {
            CatalogoCuentas cuenta = catalogoCuentasRepository.findById(asientoDTO.getCuentaId())
                    .orElseThrow(() -> new RuntimeException("Cuenta no encontrada: " + asientoDTO.getCuentaId()));
            
            AsientoContable asiento = new AsientoContable();
            asiento.setCuenta(cuenta);
            asiento.setDebe(asientoDTO.getDebe());
            asiento.setHaber(asientoDTO.getHaber());
            
            libroDiario.addAsiento(asiento);
        }
        
        return libroDiarioRepository.save(libroDiario);
    }
    
    public List<LibroDiario> obtenerTodos() {
        return libroDiarioRepository.findAllByOrderByFechaDesc();
    }
    
    public List<LibroDiario> obtenerPorRangoFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        return libroDiarioRepository.findByFechaBetweenOrderByFechaDesc(fechaInicio, fechaFin);
    }
    
    public LibroDiario obtenerPorId(Long id) {
        return libroDiarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asiento no encontrado"));
    }
    
    private String getServerIp() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            return "unknown";
        }
    }
}
