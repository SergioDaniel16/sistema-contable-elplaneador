package com.elplaneador.accounting.dto;

import java.time.LocalDate;
import java.util.List;

public class LibroDiarioDTO {
    private LocalDate fecha;
    private String descripcion;
    private List<AsientoDTO> asientos;
    
    // Constructores
    public LibroDiarioDTO() {}
    
    // Getters y Setters
    public LocalDate getFecha() {
        return fecha;
    }
    
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public List<AsientoDTO> getAsientos() {
        return asientos;
    }
    
    public void setAsientos(List<AsientoDTO> asientos) {
        this.asientos = asientos;
    }
}
