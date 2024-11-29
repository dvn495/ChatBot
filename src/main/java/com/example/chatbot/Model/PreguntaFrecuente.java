package com.example.chatbot.Model;

import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PreguntaFrecuente {
    private String pregunta;
    private String respuesta;
}
