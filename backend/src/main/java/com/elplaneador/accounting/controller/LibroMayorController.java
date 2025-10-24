package com.elplaneador.accounting.controller;

import com.elplaneador.accounting.entity.LibroMayor;
import com.elplaneador.accounting.service.LibroMayorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/libro-mayor")
@CrossOrigin(origins = "*")
public class LibroMayorController {
    
    @Autowired
    private LibroMayorService libroMayorService;
    
    @GetMapping
    public ResponseEntity<List<LibroMayor>> obtenerLibroMayor() {
        return ResponseEntity.ok(libroMayorService.generarLibroMayor());
    }
    
    @GetMapping("/rango")
    public ResponseEntity<List<LibroMayor>> obtenerPorRango(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {
        return ResponseEntity.ok(libroMayorService.generarLibroMayorPorFechas(fechaInicio, fechaFin));
    }
    
    @GetMapping("/balance")
    public ResponseEntity<Map<String, BigDecimal>> obtenerBalance() {
        return ResponseEntity.ok(libroMayorService.generarBalanceComprobacion());
    }
}
