package com.logger.api.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import java.util.concurrent.CompletableFuture;

@RestController
public class Health_Check {
    private static final Logger logger = LoggerFactory.getLogger(Health_Check.class);

    @Async
    @GetMapping("/health-check")
    public CompletableFuture<String> check_health(){
        logger.info("Health check endpoint called");
        return CompletableFuture.completedFuture("Service is running...");
    }
}
