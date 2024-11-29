package com.example.chatbot.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.chatbot.Repository.UserRepository;
import com.example.chatbot.Model.User;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/user/phone")
    public ResponseEntity<User> findByPhone(@RequestParam Long telefono) {
        return userRepository.findByTelefono(telefono)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
