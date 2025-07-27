package com.logger.api.controller;
import com.logger.api.model.dtos.LogRequestDTO;
import com.logger.api.model.dtos.LogResponseDTO;
import com.logger.api.service.LogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.scheduling.annotation.Async;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/logs")
public class Logger_Controller {
    private final LogService _logService;

    public  Logger_Controller(LogService logService){
        _logService=logService;
    }

    @Async
    @PostMapping("/create-log")
    public CompletableFuture<ResponseEntity<LogResponseDTO>> createLog(@RequestBody LogRequestDTO logEntry){
        return _logService.createLog(logEntry).thenApply(saved -> ResponseEntity.status(201).body(saved));
    }
}
