package com.elplaneador.accounting.controller;

import com.elplaneador.accounting.dto.CatalogoCuentasDTO;
import com.elplaneador.accounting.entity.CatalogoCuentas;
import com.elplaneador.accounting.service.CatalogoCuentasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/catalogo")
@CrossOrigin(origins = "*")
public class CatalogoCuentasController {
    
    @Autowired
    private CatalogoCuentasService catalogoCuentasService;
    
    @PostMapping
    public ResponseEntity<?> crearCuenta(@RequestBody CatalogoCuentasDTO dto) {
        try {
            CatalogoCuentas cuenta = catalogoCuentasService.crearCuenta(dto);
            return ResponseEntity.ok(cuenta);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @GetMapping
    public ResponseEntity<List<CatalogoCuentas>> obtenerTodas() {
        return ResponseEntity.ok(catalogoCuentasService.obtenerTodas());
    }
    
    @GetMapping("/activas")
    public ResponseEntity<List<CatalogoCuentas>> obtenerActivas() {
        return ResponseEntity.ok(catalogoCuentasService.obtenerTodasActivas());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        try {
            CatalogoCuentas cuenta = catalogoCuentasService.obtenerPorId(id);
            return ResponseEntity.ok(cuenta);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarCuenta(@PathVariable Long id, @RequestBody CatalogoCuentasDTO dto) {
        try {
            CatalogoCuentas cuenta = catalogoCuentasService.actualizarCuenta(id, dto);
            return ResponseEntity.ok(cuenta);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCuenta(@PathVariable Long id) {
        try {
            catalogoCuentasService.eliminarCuenta(id);
            return ResponseEntity.ok("Cuenta desactivada correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
