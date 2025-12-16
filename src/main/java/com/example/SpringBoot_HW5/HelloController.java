package com.example.SpringBoot_HW5;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Value("${netology.message:Hello from Netology}")
    private String message;

    @Value("${netology.profile.dev:true}")
    private boolean isDevProfile;

    @GetMapping("/")
    public String home() {
        String profile = isDevProfile ? "DEV" : "PROD";
        return message + " | Profile: " + profile;
    }

    @GetMapping("/api/hello")
    public String hello() {
        return "Hello from API! Current message: " + message;
    }

    @GetMapping("/api/status")
    public String status() {
        return "Application is running. Profile: " + (isDevProfile ? "DEV" : "PROD");
    }
}