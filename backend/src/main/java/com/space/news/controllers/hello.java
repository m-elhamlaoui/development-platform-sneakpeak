package com.space.news.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class hello {

    @GetMapping("/hello")
    public String index() {
        return "Greetings from Spring Boot!";
    }

}