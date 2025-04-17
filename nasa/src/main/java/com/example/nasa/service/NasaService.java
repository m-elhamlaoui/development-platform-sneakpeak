package com.example.nasa.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class NasaService {

    @Value("${nasa.api.key}")
    private String apiKey;

    @Value("${nasa.api.base-url}")
    private String baseUrl;

    public Map<String, String> getApod() {
        RestTemplate restTemplate = new RestTemplate();
        String url = baseUrl + "/apod?api_key=" + apiKey;
        return restTemplate.getForObject(url, Map.class);
    }
}
