package com.logger.api.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.scheduling.annotation.Async;
import java.util.concurrent.CompletableFuture;
import com.logger.api.common.AppLogger;
import org.slf4j.Logger;

@RestController
public class Health_Check {
    private final Logger logger = AppLogger.getLogger(Health_Check.class);

    @Async
    @GetMapping("/health-check")
    public CompletableFuture<String> check_health(){
        logger.info("Health check endpoint called");
        return CompletableFuture.completedFuture("Service is running...");
    }
}
