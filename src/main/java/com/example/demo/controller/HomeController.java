package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Spring Boot + Jenkins + GitHub Pipeline Success! agter ci and cd";
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello Jenkins!";
    }
}