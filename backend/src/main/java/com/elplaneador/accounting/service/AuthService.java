package com.elplaneador.accounting.service;

import com.elplaneador.accounting.dto.LoginRequest;
import com.elplaneador.accounting.dto.LoginResponse;
import com.elplaneador.accounting.entity.Usuario;
import com.elplaneador.accounting.repository.UsuarioRepository;
import com.elplaneador.accounting.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Service
public class AuthService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    public LoginResponse login(LoginRequest loginRequest) {
        Usuario usuario = usuarioRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        if (!passwordEncoder.matches(loginRequest.getPassword(), usuario.getPassword())) {
            throw new RuntimeException("Credenciales inválidas");
        }
        
        String token = jwtUtil.generateToken(usuario.getUsername());
        String serverIp = getServerIp();
        
        return new LoginResponse(token, usuario.getUsername(), usuario.getNombre(), 
                                usuario.getRol(), serverIp);
    }
    
    private String getServerIp() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            return "unknown";
        }
    }
}
