package com.example.chatbot.Controller;

import com.example.chatbot.Service.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.chatbot.Repository.UserRepository;
import com.example.chatbot.Model.User;

import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
@RestController
public class UserController {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final MessagesService messagesService;
    private final UserService userService;

    private final OpenIAService openIAService;

    private final ChatHandler chatHandler;

    @Autowired
    public UserController(JwtService jwtService, UserRepository userRepository, MessagesService messagesService, UserService userService, OpenIAService openIAService, ChatHandler chatHandler) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.messagesService = messagesService;
        this.userService = userService;
        this.openIAService = openIAService;
        this.chatHandler = chatHandler;
    }

    @GetMapping("/user/phone")
    public ResponseEntity<User> findByPhone(@RequestParam Long telefono) {
        return userRepository.findByTelefono(telefono)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/user/username")
    public ResponseEntity<String> setUsername(@RequestHeader("Authorization") String token) {
        // Validar y procesar el token
        token = token.replace("Bearer", "").trim();

        if (!jwtService.isTokenValid(token)) {
            return ResponseEntity.status(401).body("Invalid Token or Expired");
        }

        // Obtener el nombre de usuario del token
        String username = jwtService.getUsernameFromToken(token);

        // Establecer el nombre en una variable temporal
        chatHandler.setUserName(username);

        System.out.println(username);

        return ResponseEntity.ok("Username successfully set");
    }


    @PostMapping("/user/age")
    public ResponseEntity<String> addAgeByPhone(@RequestBody Map<String, Integer> requestData,
                                                @RequestHeader("Authorization") String token) {
        try {
            Integer age = requestData.get("age");
            // Validar y procesar el token
            token = token.replace("Bearer", "").trim();

            if (!jwtService.isTokenValid(token)) {
                return ResponseEntity.status(401).body("Invalid Token or Expired");
            }

            String username = jwtService.getUsernameFromToken(token);

            // Obtener el ID del usuario
            Integer userId = userService.getByUsername(username)
                    .orElseThrow(() -> new RuntimeException("User not found")).getId();

            // Actualizar la edad del usuario
            userService.updateAge(age, userId);

            return ResponseEntity.ok("Age successfully updated");
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/user/availability")
    public ResponseEntity<String> addAvailabilityByPhone(@RequestBody Map<String, String> requestData,
                                                @RequestHeader("Authorization") String token) {
        try {
            String availability = requestData.get("availability");
            // Validar y procesar el token
            token = token.replace("Bearer", "").trim();

            if (!jwtService.isTokenValid(token)) {
                return ResponseEntity.status(401).body("Invalid Token or Expired");
            }

            String username = jwtService.getUsernameFromToken(token);

            // Obtener el ID del usuario
            Integer userId = userService.getByUsername(username)
                    .orElseThrow(() -> new RuntimeException("User not found")).getId();

            // Actualizar la edad del usuario
            userService.updateAvailability(availability, userId);

            return ResponseEntity.ok("Age successfully updated");
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/user/contact")
    public ResponseEntity<String> addContactWayByPhone(@RequestBody Map<String, String> requestData,
                                                         @RequestHeader("Authorization") String token) {
        try {
            String contact_way = requestData.get("contact_way");
            // Validar y procesar el token
            token = token.replace("Bearer", "").trim();

            if (!jwtService.isTokenValid(token)) {
                return ResponseEntity.status(401).body("Invalid Token or Expired");
            }

            String username = jwtService.getUsernameFromToken(token);

            // Obtener el ID del usuario
            Integer userId = userService.getByUsername(username)
                    .orElseThrow(() -> new RuntimeException("User not found")).getId();

            // Actualizar la edad del usuario
            userService.updateContact(contact_way, userId);

            return ResponseEntity.ok("Age successfully updated");
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body("Error: " + e.getMessage());
        }
    }


}