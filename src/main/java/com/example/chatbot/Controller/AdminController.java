package com.example.chatbot.Controller;

import com.example.chatbot.Service.MessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private MessagesService messagesService;

    @GetMapping("/messages/today")
    public ResponseEntity<List<Map<String, Object>>> getTodayMessages(@RequestParam String start,
                                                                      @RequestParam String end) {
        LocalDate today = LocalDate.parse(start);
        LocalDate endDay = LocalDate.parse(end);
        List<Map<String, Object>> messages = messagesService.getMessagesByDateRange(today, endDay);
        return ResponseEntity.ok(messages);
    }


    @GetMapping("/users/today")
    public List<Map<String, Object>> getUserWhoWroteToday(@RequestParam String start,
                                                          @RequestParam String end){
        LocalDate today = LocalDate.parse(start);
        LocalDate endDay = LocalDate.parse(end);

        return messagesService.getUsersWhoWroteToday(today, endDay);
    }
}

