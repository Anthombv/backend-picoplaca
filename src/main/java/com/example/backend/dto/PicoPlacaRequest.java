package com.example.backend.dto;

public class PicoPlacaRequest {
    private String placa;
    private long timestamp; // Cambiar para recibir el timestamp

    // Getters y Setters
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}

