package com.elplaneador.accounting.service;

import com.elplaneador.accounting.entity.Bitacora;
import com.elplaneador.accounting.repository.BitacoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BitacoraService {
    
    @Autowired
    private BitacoraRepository bitacoraRepository;
    
    public void registrar(String operacion, String tabla, Long registroId, String descripcion, String usuario) {
        try {
            String serverIp = InetAddress.getLocalHost().getHostAddress();
            
            Bitacora bitacora = new Bitacora(
                operacion,
                tabla,
                registroId,
                descripcion,
                usuario != null ? usuario : "SISTEMA",
                serverIp
            );
            
            bitacoraRepository.save(bitacora);
        } catch (Exception e) {
            System.err.println("Error al registrar en bitácora: " + e.getMessage());
        }
    }
    
    public List<Bitacora> obtenerUltimas100() {
        return bitacoraRepository.findTop100ByOrderByFechaHoraDesc();
    }
    
    public List<Bitacora> obtenerPorNodo(String serverIp) {
        return bitacoraRepository.findByServerIp(serverIp);
    }
    
    public List<Bitacora> obtenerPorTabla(String tabla) {
        return bitacoraRepository.findByTablaAfectada(tabla);
    }
    
    public List<Bitacora> obtenerPorRangoFechas(LocalDateTime inicio, LocalDateTime fin) {
        return bitacoraRepository.findByFechaHoraBetween(inicio, fin);
    }
}
