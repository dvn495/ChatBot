package com.example.chatbot.Service;

import com.example.chatbot.Model.User;
import com.example.chatbot.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public Optional<User> getByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public Optional<User> getByPhone(Long telefono){
        return userRepository.findByTelefono(telefono);
    }

}
