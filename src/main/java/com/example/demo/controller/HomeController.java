package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Spring Boot + Jenkins + GitHub Pipeline Success!";
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello Jenkins!";
    }
}