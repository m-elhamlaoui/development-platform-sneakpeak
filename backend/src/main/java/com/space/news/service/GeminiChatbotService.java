package com.space.news.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

@Service
public class GeminiChatbotService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper; // Jackson ObjectMapper for JSON parsing
    private final String geminiApiKey = "AIzaSyD80icQDemjgX2fogW_p61SxVPF6Fn0Cgk"; // Replace with your actual API key
    private final String geminiApiUrl = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent";

    public GeminiChatbotService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    /**
     * Sends a prompt to the Gemini API and retrieves the generated text.
     *
     * @param userPrompt The text prompt from the user.
     * @return The generated text from the Gemini model, or an error message if something goes wrong.
     */
    public String generateContent(String userMessage) {
    String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=" + geminiApiKey;

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    List<Map<String, Object>> contents = List.of(
        Map.of(
            "parts", List.of(Map.of("text", "You are a friendly astronomy expert assistant. Include quotes and emojis.")),
            "role", "model"
        ),
        Map.of(
            "parts", List.of(Map.of("text", userMessage)),
            "role", "user"
        )
    );

    Map<String, Object> body = Map.of("contents", contents);

    HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

    try {
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        String jsonResponse = response.getBody();
        System.out.println("Gemini API raw response: " + jsonResponse);  // Log the raw response

        JsonNode rootNode = objectMapper.readTree(jsonResponse);

        JsonNode candidatesNode = rootNode.path("candidates");

        if (candidatesNode.isArray() && candidatesNode.size() > 0) {
            JsonNode firstCandidate = candidatesNode.get(0);
            JsonNode contentNode = firstCandidate.path("content");
            JsonNode partsNode = contentNode.path("parts");

            if (partsNode.isArray() && partsNode.size() > 0) {
                JsonNode firstPart = partsNode.get(0);
                JsonNode textNode = firstPart.path("text");

                if (textNode.isTextual()) {
                    return textNode.asText();
                } else {
                    return "Error: 'text' field not found or not textual in response.";
                }
            } else {
                return "Error: 'parts' array empty or not found in response.";
            }
        } else {
            return "Error: 'candidates' array empty or not found in response.";
        }
    } catch (Exception e) {
        return "Error calling Gemini API: " + e.getMessage();
    }
}

}

