package com.tecsup.demo.domain.entities;

public class AuthResponse {
    private String token;
    private Usuario usuario;

    public AuthResponse(String token, Usuario usuario) {
        this.token = token;
        this.usuario = usuario;
    }

    public String getToken() {
        return token;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}