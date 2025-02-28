package com.example.benchmark;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import com.example.benchmark.service.RateLimiterService; 

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
public class RateLimiterBenchmark {

    private RateLimiterService rateLimiterService;

    @Setup(Level.Trial)
    public void setup() {
        rateLimiterService = new RateLimiterService();
    }

    @Benchmark
    public boolean testRateLimiter() {
        return rateLimiterService.handleRequest();
    }
}
