package com.example.benchmark.util;

public class TokenBucket {

    private final long capacity;
    private final long refillRate;
    private long tokens;
    private long lastRefillTimestamp;

    public TokenBucket(long capacity, long rate) {
        this.capacity = capacity;
        this.refillRate = rate;
        this.tokens = capacity;
        this.lastRefillTimestamp = System.currentTimeMillis();
    }

    public synchronized boolean consume() {
        refill();
        if (tokens > 0) {
            tokens--;
            return true; // Request allowed
        }
        return false; // Rate limit exceeded
    }

    private void refill() {
        long now = System.nanoTime();
        long timeElapsed = now - lastRefillTimestamp;
        int newTokens = (int) (timeElapsed / 1_000_000_000.0 * lastRefillTimestamp);
        tokens = Math.min(tokens + newTokens, capacity);
        lastRefillTimestamp = now;
    }
}
