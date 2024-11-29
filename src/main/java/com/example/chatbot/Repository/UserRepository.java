package com.example.chatbot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.chatbot.Model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
    Optional<User> findByTelefono(Long telefono);
}
