package com.elplaneador.accounting.controller;

import com.elplaneador.accounting.dto.LibroDiarioDTO;
import com.elplaneador.accounting.entity.LibroDiario;
import com.elplaneador.accounting.service.LibroDiarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/libro-diario")
@CrossOrigin(origins = "*")
public class LibroDiarioController {
    
    @Autowired
    private LibroDiarioService libroDiarioService;
    
    @PostMapping
    public ResponseEntity<?> registrarAsiento(@RequestBody LibroDiarioDTO dto) {
        try {
            LibroDiario libroDiario = libroDiarioService.registrarAsiento(dto);
            return ResponseEntity.ok(libroDiario);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @GetMapping
    public ResponseEntity<List<LibroDiario>> obtenerTodos() {
        return ResponseEntity.ok(libroDiarioService.obtenerTodos());
    }
    
    @GetMapping("/rango")
    public ResponseEntity<List<LibroDiario>> obtenerPorRango(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {
        return ResponseEntity.ok(libroDiarioService.obtenerPorRangoFechas(fechaInicio, fechaFin));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        try {
            LibroDiario libroDiario = libroDiarioService.obtenerPorId(id);
            return ResponseEntity.ok(libroDiario);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
