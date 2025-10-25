package com.elplaneador.accounting.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bitacora")
public class Bitacora {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;
    
    @Column(nullable = false)
    private String operacion;
    
    @Column(name = "tabla_afectada", nullable = false)
    private String tablaAfectada;
    
    @Column(name = "registro_id")
    private Long registroId;
    
    @Column(columnDefinition = "TEXT")
    private String descripcion;
    
    private String usuario;
    
    @Column(name = "server_ip", nullable = false)
    private String serverIp;
    
    @Column(name = "datos_json", columnDefinition = "TEXT")
    private String datosJson;
    
    // Constructor vacío
    public Bitacora() {
        this.fechaHora = LocalDateTime.now();
    }
    
    // Constructor con parámetros principales
    public Bitacora(String operacion, String tablaAfectada, Long registroId, String descripcion, String usuario, String serverIp) {
        this.fechaHora = LocalDateTime.now();
        this.operacion = operacion;
        this.tablaAfectada = tablaAfectada;
        this.registroId = registroId;
        this.descripcion = descripcion;
        this.usuario = usuario;
        this.serverIp = serverIp;
    }
    
    // Getters y Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }
    
    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }
    
    public String getOperacion() {
        return operacion;
    }
    
    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }
    
    public String getTablaAfectada() {
        return tablaAfectada;
    }
    
    public void setTablaAfectada(String tablaAfectada) {
        this.tablaAfectada = tablaAfectada;
    }
    
    public Long getRegistroId() {
        return registroId;
    }
    
    public void setRegistroId(Long registroId) {
        this.registroId = registroId;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getUsuario() {
        return usuario;
    }
    
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    public String getServerIp() {
        return serverIp;
    }
    
    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }
    
    public String getDatosJson() {
        return datosJson;
    }
    
    public void setDatosJson(String datosJson) {
        this.datosJson = datosJson;
    }
}
