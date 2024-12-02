package com.example.chatbot.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/password")
public class PasswordController {

    private static final String password = "campuslands2023";

    @GetMapping("/get")
    public ResponseEntity<Map<String, String>> getPassword(){
        return ResponseEntity.ok(Map.of("password",password));
    }
}
