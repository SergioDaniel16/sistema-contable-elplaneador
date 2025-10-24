package com.elplaneador.accounting.dto;

public class LoginResponse {
    private String token;
    private String username;
    private String nombre;
    private String rol;
    private String serverIp;
    
    // Constructores
    public LoginResponse() {}
    
    public LoginResponse(String token, String username, String nombre, String rol, String serverIp) {
        this.token = token;
        this.username = username;
        this.nombre = nombre;
        this.rol = rol;
        this.serverIp = serverIp;
    }
    
    // Getters y Setters
    public String getToken() {
        return token;
    }
    
    public void setToken(String token) {
        this.token = token;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getRol() {
        return rol;
    }
    
    public void setRol(String rol) {
        this.rol = rol;
    }
    
    public String getServerIp() {
        return serverIp;
    }
    
    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }
}
