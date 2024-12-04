package com.example.chatbot.Service;

import com.example.chatbot.Model.User;
import com.example.chatbot.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
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

    public int updateAge(Integer age, Integer userId) {
        int rowsAffected = userRepository.updateUserAgeById(age, userId);
        if (rowsAffected == 0) {
            throw new RuntimeException("No se pudo actualizar la edad. Usuario no encontrado.");
        }
        return rowsAffected;
    }

    public int updateAvailability(String availability, Integer userId) {
        int rowsAffected = userRepository.updateAvailabilityAgeById(availability, userId);
        if (rowsAffected == 0) {
            throw new RuntimeException("No se pudo actualizar la edad. Usuario no encontrado.");
        }
        return rowsAffected;
    }


}
