package com.elplaneador.accounting.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "libro_diario")
public class LibroDiario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private LocalDate fecha;
    
    @Column(nullable = false, length = 500)
    private String descripcion;
    
    @OneToMany(mappedBy = "libroDiario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AsientoContable> asientos = new ArrayList<>();
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "server_ip", length = 45)
    private String serverIp;
    
    // Constructor
    public LibroDiario() {
        this.createdAt = LocalDateTime.now();
    }
    
    // Método para agregar asiento
    public void addAsiento(AsientoContable asiento) {
        asientos.add(asiento);
        asiento.setLibroDiario(this);
    }
    
    // Getters y Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
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
    
    public List<AsientoContable> getAsientos() {
        return asientos;
    }
    
    public void setAsientos(List<AsientoContable> asientos) {
        this.asientos = asientos;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public String getServerIp() {
        return serverIp;
    }
    
    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }
}
