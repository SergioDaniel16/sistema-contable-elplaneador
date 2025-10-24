package com.elplaneador.accounting.service;

import com.elplaneador.accounting.entity.AsientoContable;
import com.elplaneador.accounting.entity.CatalogoCuentas;
import com.elplaneador.accounting.entity.LibroDiario;
import com.elplaneador.accounting.entity.LibroMayor;
import com.elplaneador.accounting.repository.CatalogoCuentasRepository;
import com.elplaneador.accounting.repository.LibroDiarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class LibroMayorService {
    
    @Autowired
    private LibroDiarioRepository libroDiarioRepository;
    
    @Autowired
    private CatalogoCuentasRepository catalogoCuentasRepository;
    
    public List<LibroMayor> generarLibroMayor() {
        return generarLibroMayorPorFechas(null, null);
    }
    
    public List<LibroMayor> generarLibroMayorPorFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        List<LibroDiario> registros;
        
        if (fechaInicio != null && fechaFin != null) {
            registros = libroDiarioRepository.findByFechaBetweenOrderByFechaDesc(fechaInicio, fechaFin);
        } else {
            registros = libroDiarioRepository.findAll();
        }
        
        // Agrupar por cuenta y sumar debe/haber
        Map<Long, LibroMayor> mayorMap = new HashMap<>();
        
        for (LibroDiario registro : registros) {
            for (AsientoContable asiento : registro.getAsientos()) {
                Long cuentaId = asiento.getCuenta().getId();
                
                if (!mayorMap.containsKey(cuentaId)) {
                    mayorMap.put(cuentaId, new LibroMayor(
                        asiento.getCuenta(),
                        BigDecimal.ZERO,
                        BigDecimal.ZERO
                    ));
                }
                
                LibroMayor mayor = mayorMap.get(cuentaId);
                mayor.setTotalDebe(mayor.getTotalDebe().add(asiento.getDebe()));
                mayor.setTotalHaber(mayor.getTotalHaber().add(asiento.getHaber()));
                
                // Recalcular saldo
                CatalogoCuentas cuenta = mayor.getCuenta();
                if ("DEUDORA".equals(cuenta.getNaturaleza())) {
                    mayor.setSaldo(mayor.getTotalDebe().subtract(mayor.getTotalHaber()));
                } else {
                    mayor.setSaldo(mayor.getTotalHaber().subtract(mayor.getTotalDebe()));
                }
            }
        }
        
        return new ArrayList<>(mayorMap.values())
                .stream()
                .sorted(Comparator.comparing(m -> m.getCuenta().getCodigo()))
                .collect(Collectors.toList());
    }
    
    public Map<String, BigDecimal> generarBalanceComprobacion() {
        List<LibroMayor> mayor = generarLibroMayor();
        
        BigDecimal totalDeudor = BigDecimal.ZERO;
        BigDecimal totalAcreedor = BigDecimal.ZERO;
        
        for (LibroMayor item : mayor) {
            if (item.getSaldo().compareTo(BigDecimal.ZERO) > 0) {
                if ("DEUDORA".equals(item.getCuenta().getNaturaleza())) {
                    totalDeudor = totalDeudor.add(item.getSaldo());
                } else {
                    totalAcreedor = totalAcreedor.add(item.getSaldo());
                }
            }
        }
        
        Map<String, BigDecimal> balance = new HashMap<>();
        balance.put("totalDeudor", totalDeudor);
        balance.put("totalAcreedor", totalAcreedor);
        balance.put("diferencia", totalDeudor.subtract(totalAcreedor).abs());
        
        return balance;
    }
}
