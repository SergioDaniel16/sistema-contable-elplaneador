package com.elplaneador.accounting.dto;

public class CatalogoCuentasDTO {
    private Long id;
    private String codigo;
    private String nombre;
    private String tipo;
    private String naturaleza;
    private Boolean activa;
    
    // Constructores
    public CatalogoCuentasDTO() {}
    
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
    
    public Boolean getActiva() {
        return activa;
    }
    
    public void setActiva(Boolean activa) {
        this.activa = activa;
    }
}
