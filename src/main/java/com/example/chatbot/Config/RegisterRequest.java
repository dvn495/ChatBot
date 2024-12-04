package com.example.chatbot.Config;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private Long telefono;
    private String role;
    private String city;

    // Constructor vacío
    public RegisterRequest() {
    }

    // Constructor con parámetros
    public RegisterRequest(String username, Long telefono, String role, String city) {
        this.username = username;
        this.telefono = telefono;
        this.role = role;
        this.city = city;
    }

    // Getter y Setter
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getTelefono() {
        return telefono;
    }

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
