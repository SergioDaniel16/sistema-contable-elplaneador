package com.elplaneador.accounting.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "catalogo_cuentas")
public class CatalogoCuentas {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false, length = 20)
    private String codigo;
    
    @Column(nullable = false, length = 200)
    private String nombre;
    
    @Column(nullable = false, length = 50)
    private String tipo; // ACTIVO, PASIVO, CAPITAL, INGRESO, GASTO
    
    @Column(nullable = false, length = 20)
    private String naturaleza; // DEUDORA, ACREEDORA
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "server_ip", length = 45)
    private String serverIp;
    
    private Boolean activa = true;
    
    // Constructor
    public CatalogoCuentas() {
        this.createdAt = LocalDateTime.now();
    }
    
    // Getters y Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getCodigo() {
        return codigo;
    }
    
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getTipo() {
        return tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public String getNaturaleza() {
        return naturaleza;
    }
    
    public void setNaturaleza(String naturaleza) {
        this.naturaleza = naturaleza;
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
    
    public Boolean getActiva() {
        return activa;
    }
    
    public void setActiva(Boolean activa) {
        this.activa = activa;
    }
}
