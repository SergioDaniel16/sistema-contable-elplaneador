package com.elplaneador.accounting.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;

@Entity
@Table(name = "asientos_contables")
public class AsientoContable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "libro_diario_id", nullable = false)
    @JsonIgnore
    private LibroDiario libroDiario;
    
    @ManyToOne
    @JoinColumn(name = "cuenta_id", nullable = false)
    private CatalogoCuentas cuenta;
    
    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal debe = BigDecimal.ZERO;
    
    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal haber = BigDecimal.ZERO;
    
    // Constructor
    public AsientoContable() {}
    
    // Getters y Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public LibroDiario getLibroDiario() {
        return libroDiario;
    }
    
    public void setLibroDiario(LibroDiario libroDiario) {
        this.libroDiario = libroDiario;
    }
    
    public CatalogoCuentas getCuenta() {
        return cuenta;
    }
    
    public void setCuenta(CatalogoCuentas cuenta) {
        this.cuenta = cuenta;
    }
    
    public BigDecimal getDebe() {
        return debe;
    }
    
    public void setDebe(BigDecimal debe) {
        this.debe = debe;
    }
    
    public BigDecimal getHaber() {
        return haber;
    }
    
    public void setHaber(BigDecimal haber) {
        this.haber = haber;
    }
}
