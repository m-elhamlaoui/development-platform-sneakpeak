package com.example.nasa.controller;

import com.example.nasa.service.NasaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class NasaController {

    @Autowired
    private NasaService nasaService;

    @GetMapping("/")
public String home() {
    return "home"; // affiche home.html
}

@GetMapping("/apod")
public String getApod(Model model) {
    Map<String, String> apod = nasaService.getApod();
    model.addAttribute("apod", apod);
    return "index"; // affiche index.html avec les données API
}

}
