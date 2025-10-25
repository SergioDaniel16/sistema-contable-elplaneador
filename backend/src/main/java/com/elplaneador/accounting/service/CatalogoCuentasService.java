package com.elplaneador.accounting.service;

import com.elplaneador.accounting.dto.CatalogoCuentasDTO;
import com.elplaneador.accounting.entity.CatalogoCuentas;
import com.elplaneador.accounting.repository.CatalogoCuentasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;

@Service
public class CatalogoCuentasService {
    
    @Autowired
    private CatalogoCuentasRepository catalogoCuentasRepository;
    
    @Autowired
    private BitacoraService bitacoraService;
    
    public List<CatalogoCuentas> obtenerActivas() {
        bitacoraService.registrar("CONSULTA", "catalogo_cuentas", null, "Consulta de cuentas activas", "admin");
        return catalogoCuentasRepository.findByActivaTrue();
    }
    
    public List<CatalogoCuentas> obtenerTodas() {
        bitacoraService.registrar("CONSULTA", "catalogo_cuentas", null, "Consulta de todas las cuentas", "admin");
        return catalogoCuentasRepository.findAll();
    }
    
    public CatalogoCuentas crear(CatalogoCuentasDTO dto) {
        try {
            String serverIp = InetAddress.getLocalHost().getHostAddress();
            
            CatalogoCuentas cuenta = new CatalogoCuentas();
            cuenta.setCodigo(dto.getCodigo());
            cuenta.setNombre(dto.getNombre());
            cuenta.setTipo(dto.getTipo());
            cuenta.setNaturaleza(dto.getNaturaleza());
            cuenta.setServerIp(serverIp);
            cuenta.setActiva(true);
            
            CatalogoCuentas cuentaGuardada = catalogoCuentasRepository.save(cuenta);
            
            // Registrar en bitácora
            bitacoraService.registrar(
                "CREATE",
                "catalogo_cuentas",
                cuentaGuardada.getId(),
                "Cuenta creada: " + cuenta.getCodigo() + " - " + cuenta.getNombre(),
                "admin"
            );
            
            return cuentaGuardada;
        } catch (UnknownHostException e) {
            throw new RuntimeException("Error al obtener IP del servidor");
        }
    }
    
    public CatalogoCuentas actualizar(Long id, CatalogoCuentasDTO dto) {
        try {
            String serverIp = InetAddress.getLocalHost().getHostAddress();
            
            Optional<CatalogoCuentas> cuentaExistente = catalogoCuentasRepository.findById(id);
            if (cuentaExistente.isPresent()) {
                CatalogoCuentas cuenta = cuentaExistente.get();
                cuenta.setNombre(dto.getNombre());
                cuenta.setTipo(dto.getTipo());
                cuenta.setNaturaleza(dto.getNaturaleza());
                cuenta.setServerIp(serverIp);
                
                CatalogoCuentas resultado = catalogoCuentasRepository.save(cuenta);
                
                // Registrar en bitácora
                bitacoraService.registrar(
                    "UPDATE",
                    "catalogo_cuentas",
                    id,
                    "Cuenta actualizada: " + cuenta.getCodigo() + " - " + cuenta.getNombre(),
                    "admin"
                );
                
                return resultado;
            }
            throw new RuntimeException("Cuenta no encontrada");
        } catch (UnknownHostException e) {
            throw new RuntimeException("Error al obtener IP del servidor");
        }
    }
    
    public void eliminar(Long id) {
        Optional<CatalogoCuentas> cuenta = catalogoCuentasRepository.findById(id);
        if (cuenta.isPresent()) {
            // Registrar en bitácora ANTES de eliminar
            bitacoraService.registrar(
                "DELETE",
                "catalogo_cuentas",
                id,
                "Cuenta eliminada: " + cuenta.get().getCodigo() + " - " + cuenta.get().getNombre(),
                "admin"
            );
            
            catalogoCuentasRepository.deleteById(id);
        }
    }
}
