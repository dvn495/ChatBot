package com.example.chatbot.Service;

import com.example.chatbot.Model.Messages;
import com.example.chatbot.Model.User;
import com.example.chatbot.Repository.MessagesRepository;
import com.example.chatbot.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MessagesService {

    @Autowired
    private MessagesRepository messagesRepository;
    @Autowired
    private UserRepository userRepository;

    public void saveQuestionUser(String preguntaUsuario, Integer userId){
        Messages messages = new Messages();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User don't found"));

        messages.setContenido(preguntaUsuario);
        messages.setHoraMensaje(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
        messages.setUser(user);
        messagesRepository.save(messages);
    }

    public List<Map<String, Object>> getMessagesByDateRange(LocalDate startDate, LocalDate endDate, String city) {
        LocalDateTime startOfDay = startDate.atStartOfDay();
        LocalDateTime endOfDay = endDate.atTime(LocalTime.MAX);

        return messagesRepository.findMessagesByDateRange(startOfDay, endOfDay, city);
    }


    public List<Map<String, Object>> getUsersWhoWroteToday(LocalDate startDate, LocalDate endDate, String city){
        LocalDateTime startOfDay = startDate.atStartOfDay();
        LocalDateTime endOfDay = endDate.atTime(LocalTime.MAX);

        return messagesRepository.findDistinctUserDetailsByDateRange(startOfDay, endOfDay, city);

    }




}
