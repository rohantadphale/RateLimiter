package com.example.benchmark.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.benchmark.service.RateLimiterService;

@RestController
public class RateLimitController {

    private final RateLimiterService rateLimiterService;

    public RateLimitController(RateLimiterService rateLimiterService) {
        this.rateLimiterService = rateLimiterService;
    }

    @GetMapping("/api/limited-resource")
    public ResponseEntity<String> getRateLimitedResource() {
        if (rateLimiterService.handleRequest()) {
            return ResponseEntity.ok("Request accepted");
        }
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body("Rate limit exceeded");
    }
}
