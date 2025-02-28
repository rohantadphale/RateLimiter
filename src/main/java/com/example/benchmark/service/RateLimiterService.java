package com.example.benchmark.service;

import org.springframework.stereotype.Service;

import com.example.benchmark.util.TokenBucket;

@Service
public class RateLimiterService {

    private final TokenBucket tokenBucket;

    public RateLimiterService() {
        // Set capacity and refill rate (e.g., 5 tokens per second)
        this.tokenBucket = new TokenBucket(5, 5);
    }

    public boolean handleRequest() {
        return tokenBucket.consume();
    }
}