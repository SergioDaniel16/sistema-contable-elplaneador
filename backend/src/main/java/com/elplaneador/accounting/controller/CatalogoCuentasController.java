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
    public ResponseEntity<CatalogoCuentas> crear(@RequestBody CatalogoCuentasDTO dto) {
        return ResponseEntity.ok(catalogoCuentasService.crear(dto));
    }
    
    @GetMapping
    public ResponseEntity<List<CatalogoCuentas>> obtenerTodas() {
        return ResponseEntity.ok(catalogoCuentasService.obtenerTodas());
    }
    
    @GetMapping("/activas")
    public ResponseEntity<List<CatalogoCuentas>> obtenerActivas() {
        return ResponseEntity.ok(catalogoCuentasService.obtenerActivas());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<CatalogoCuentas> actualizar(
            @PathVariable Long id, 
            @RequestBody CatalogoCuentasDTO dto) {
        return ResponseEntity.ok(catalogoCuentasService.actualizar(id, dto));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        catalogoCuentasService.eliminar(id);
        return ResponseEntity.ok().build();
    }
}
