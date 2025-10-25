package com.elplaneador.accounting.controller;

import com.elplaneador.accounting.entity.Bitacora;
import com.elplaneador.accounting.service.BitacoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bitacora")
@CrossOrigin(origins = "*")
public class BitacoraController {
    
    @Autowired
    private BitacoraService bitacoraService;
    
    @GetMapping
    public ResponseEntity<List<Bitacora>> obtenerUltimas() {
        return ResponseEntity.ok(bitacoraService.obtenerUltimas100());
    }
    
    @GetMapping("/nodo/{serverIp}")
    public ResponseEntity<List<Bitacora>> obtenerPorNodo(@PathVariable String serverIp) {
        return ResponseEntity.ok(bitacoraService.obtenerPorNodo(serverIp));
    }
    
    @GetMapping("/tabla/{tabla}")
    public ResponseEntity<List<Bitacora>> obtenerPorTabla(@PathVariable String tabla) {
        return ResponseEntity.ok(bitacoraService.obtenerPorTabla(tabla));
    }
}
