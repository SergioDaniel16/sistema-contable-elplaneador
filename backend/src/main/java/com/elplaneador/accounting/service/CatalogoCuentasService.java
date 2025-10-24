package com.elplaneador.accounting.service;

import com.elplaneador.accounting.dto.CatalogoCuentasDTO;
import com.elplaneador.accounting.entity.CatalogoCuentas;
import com.elplaneador.accounting.repository.CatalogoCuentasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

@Service
public class CatalogoCuentasService {
    
    @Autowired
    private CatalogoCuentasRepository catalogoCuentasRepository;
    
    public CatalogoCuentas crearCuenta(CatalogoCuentasDTO dto) {
        if (catalogoCuentasRepository.existsByCodigo(dto.getCodigo())) {
            throw new RuntimeException("El código de cuenta ya existe");
        }
        
        CatalogoCuentas cuenta = new CatalogoCuentas();
        cuenta.setCodigo(dto.getCodigo());
        cuenta.setNombre(dto.getNombre());
        cuenta.setTipo(dto.getTipo());
        cuenta.setNaturaleza(dto.getNaturaleza());
        cuenta.setActiva(true);
        cuenta.setServerIp(getServerIp());
        
        return catalogoCuentasRepository.save(cuenta);
    }
    
    public List<CatalogoCuentas> obtenerTodasActivas() {
        return catalogoCuentasRepository.findByActivaTrueOrderByCodigo();
    }
    
    public List<CatalogoCuentas> obtenerTodas() {
        return catalogoCuentasRepository.findAll();
    }
    
    public CatalogoCuentas obtenerPorId(Long id) {
        return catalogoCuentasRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
    }
    
    public CatalogoCuentas actualizarCuenta(Long id, CatalogoCuentasDTO dto) {
        CatalogoCuentas cuenta = obtenerPorId(id);
        cuenta.setNombre(dto.getNombre());
        cuenta.setTipo(dto.getTipo());
        cuenta.setNaturaleza(dto.getNaturaleza());
        cuenta.setActiva(dto.getActiva());
        
        return catalogoCuentasRepository.save(cuenta);
    }
    
    public void eliminarCuenta(Long id) {
        CatalogoCuentas cuenta = obtenerPorId(id);
        cuenta.setActiva(false);
        catalogoCuentasRepository.save(cuenta);
    }
    
    private String getServerIp() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            return "unknown";
        }
    }
}
