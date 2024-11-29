package com.example.chatbot.Controller;

import com.example.chatbot.Service.JwtService;
import com.example.chatbot.Service.MessagesService;
import com.example.chatbot.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessagesService messagesService;
    private final JwtService jwtService;
    private final UserService userService;
    @PostMapping(value = "add")
    public ResponseEntity<String> saveMessage(@RequestBody Object body,
                                              @RequestHeader("Authorization") String token) {
        System.out.println("Received body: " + body);

        String preguntaUsuario;

        // Verificar el tipo de "body"
        if (body instanceof Map) {
            @SuppressWarnings("unchecked") // Suprimir advertencia de conversión
            Map<String, String> mapBody = (Map<String, String>) body;
            preguntaUsuario = mapBody.get("message");
            if (preguntaUsuario == null || preguntaUsuario.trim().isEmpty()) {
                return ResponseEntity.badRequest().body("The 'message' field is required");
            }
        } else if (body instanceof String) {
            preguntaUsuario = (String) body;
            if (preguntaUsuario.trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Message cannot be empty");
            }
        } else {
            return ResponseEntity.badRequest().body("Invalid body format");
        }

        // Validar y procesar el token
        token = token.replace("Bearer", "").trim();

        if (!jwtService.isTokenValid(token)) {
            return ResponseEntity.status(401).body("Invalid Token or Expired");
        }

        String username = jwtService.getUsernameFromToken(token);

        Integer userId = userService.getByUsername(username)
                .orElseThrow(() -> new RuntimeException("Phone not found")).getId();

        // Guardar el mensaje usando el servicio
        messagesService.saveQuestionUser(preguntaUsuario, userId);

        return ResponseEntity.ok("Message successfully saved");
    }

}
