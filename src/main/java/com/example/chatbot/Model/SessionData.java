package com.example.chatbot.Model;

public class SessionData {

    private String username;
    private long lastRequestTime;

    // Constructor vacío
    public SessionData() {
        this.lastRequestTime = 0; // Inicializa el tiempo de la última solicitud
    }

    // Getter y Setter para username
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Getter y Setter para lastRequestTime
    public long getLastRequestTime() {
        return lastRequestTime;
    }

    public void setLastRequestTime(long lastRequestTime) {
        this.lastRequestTime = lastRequestTime;
    }
}

