package com.elplaneador.accounting.entity;

import java.math.BigDecimal;

public class LibroMayor {
    private CatalogoCuentas cuenta;
    private BigDecimal totalDebe;
    private BigDecimal totalHaber;
    private BigDecimal saldo;
    
    // Constructor
    public LibroMayor(CatalogoCuentas cuenta, BigDecimal totalDebe, BigDecimal totalHaber) {
        this.cuenta = cuenta;
        this.totalDebe = totalDebe;
        this.totalHaber = totalHaber;
        
        // Calcular saldo según naturaleza de la cuenta
        if ("DEUDORA".equals(cuenta.getNaturaleza())) {
            this.saldo = totalDebe.subtract(totalHaber);
        } else {
            this.saldo = totalHaber.subtract(totalDebe);
        }
    }
    
    // Getters y Setters
    public CatalogoCuentas getCuenta() {
        return cuenta;
    }
    
    public void setCuenta(CatalogoCuentas cuenta) {
        this.cuenta = cuenta;
    }
    
    public BigDecimal getTotalDebe() {
        return totalDebe;
    }
    
    public void setTotalDebe(BigDecimal totalDebe) {
        this.totalDebe = totalDebe;
    }
    
    public BigDecimal getTotalHaber() {
        return totalHaber;
    }
    
    public void setTotalHaber(BigDecimal totalHaber) {
        this.totalHaber = totalHaber;
    }
    
    public BigDecimal getSaldo() {
        return saldo;
    }
    
    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }
}
