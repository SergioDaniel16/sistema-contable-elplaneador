package com.elplaneador.accounting.dto;

import java.math.BigDecimal;

public class AsientoDTO {
    private Long cuentaId;
    private BigDecimal debe;
    private BigDecimal haber;
    
    // Constructores
    public AsientoDTO() {}
    
    // Getters y Setters
    public Long getCuentaId() {
        return cuentaId;
    }
    
    public void setCuentaId(Long cuentaId) {
        this.cuentaId = cuentaId;
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
