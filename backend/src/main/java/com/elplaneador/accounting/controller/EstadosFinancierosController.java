package com.elplaneador.accounting.controller;

import com.elplaneador.accounting.service.EstadosFinancierosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/estados-financieros")
@CrossOrigin(origins = "*")
public class EstadosFinancierosController {
    
    @Autowired
    private EstadosFinancierosService estadosFinancierosService;
    
    @GetMapping("/balance-general")
    public ResponseEntity<Map<String, Object>> obtenerBalanceGeneral() {
        return ResponseEntity.ok(estadosFinancierosService.generarBalanceGeneral());
    }
    
    @GetMapping("/estado-resultados")
    public ResponseEntity<Map<String, Object>> obtenerEstadoResultados() {
        return ResponseEntity.ok(estadosFinancierosService.generarEstadoResultados());
    }
}
