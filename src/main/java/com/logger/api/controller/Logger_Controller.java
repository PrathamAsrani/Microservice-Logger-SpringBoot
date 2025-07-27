package com.logger.api.controller;
import com.logger.api.model.LogEntry;
import com.logger.api.service.LogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/logs")
public class Logger_Controller {
    private final LogService _logService;

    public  Logger_Controller(LogService logService){
        _logService=logService;
    }

    @PostMapping
    public ResponseEntity<LogEntry> createLog(@RequestBody LogEntry logEntry){
        LogEntry saved = _logService.createLog(logEntry);
        return ResponseEntity.status(201).body(saved);
    }
}
