package com.bookstore.bookstore.metrics;

import org.springframework.stereotype.Component;

import io.micrometer.core.instrument.MeterRegistry;
import jakarta.annotation.PostConstruct;

@Component
public class CustomMetrics {

    private final MeterRegistry meterRegistry;

    public CustomMetrics(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @PostConstruct
    public void init() {
        // Register a custom counter metric
        meterRegistry.counter("custom_metric_books_processed");

        // Register a custom gauge metric
        meterRegistry.gauge("custom_metric_active_sessions", this, CustomMetrics::getActiveSessions);
    }

    // Custom method to be used by the gauge metric
    private double getActiveSessions() {
        // Example logic to determine the number of active sessions
        return Math.random() * 100;  // Replace with actual logic
    }
}
