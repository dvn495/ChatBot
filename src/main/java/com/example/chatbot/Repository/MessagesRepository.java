package com.example.chatbot.Repository;

import com.example.chatbot.Model.Messages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface MessagesRepository extends JpaRepository<Messages, Long> {
    @Query("SELECT new map(m.id as messageId, m.user.id as userId, m.contenido as content, m.horaMensaje as messageTime) " +
            "FROM Messages m " +
            "WHERE m.horaMensaje BETWEEN :startOfDay AND :endOfDay " +
            "AND m.user.city = :city")
    List<Map<String, Object>> findMessagesByDateRange(@Param("startOfDay") LocalDateTime startOfDay,
                                                                 @Param("endOfDay") LocalDateTime endOfDay,
                                                                 @Param("city") String city);


    @Query("SELECT DISTINCT new map(m.user.id as id, m.user.username as username, m.user.telefono as telefono, m.user.city as city, m.user.age as age, m.user.availability as availability) " +
            "FROM Messages m " +
            "WHERE m.horaMensaje BETWEEN :startOfDay AND :endOfDay " +
            "AND m.user.city = :city")
    List<Map<String, Object>> findDistinctUserDetailsByDateRange(@Param("startOfDay") LocalDateTime startOfDay,
                                                                            @Param("endOfDay") LocalDateTime endOfDay,
                                                                            @Param("city") String city);


}
