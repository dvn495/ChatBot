package com.example.chatbot.Repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.chatbot.Model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
    Optional<User> findByTelefono(Long telefono);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.age = :age WHERE u.id = :userId")
    int updateUserAgeById(@Param("age") Integer age,
                          @Param("userId") Integer userId);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.availability = :availability WHERE u.id = :userId")
    int updateAvailabilityAgeById(@Param("availability") String availability,
                          @Param("userId") Integer userId);


}
