package com.space.news.controller;

import com.space.news.service.GeminiChatbotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
public class GeminiController {

    private final GeminiChatbotService geminiService;

    @Autowired
    public GeminiController(GeminiChatbotService geminiService) {
        this.geminiService = geminiService;
    }

    @PostMapping("/chat")
    public ResponseEntity<String> chat(@RequestBody String prompt) {
        String response = geminiService.generateContent(prompt);
        return ResponseEntity.ok(response);
    }
}

