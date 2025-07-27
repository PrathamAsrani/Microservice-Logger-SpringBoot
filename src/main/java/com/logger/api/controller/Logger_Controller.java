package com.logger.api.controller;
import com.logger.api.common.AppLogger;
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
import org.slf4j.Logger;

@RestController
@RequestMapping("/api/logs")
public class Logger_Controller {
    private LogService _logService;
    private final Logger logger = AppLogger.getLogger(Logger_Controller.class);

    public  Logger_Controller(LogService logService){
        try {
            _logService = logService;
            this.logger.info("logservice injected");
        }
        catch(Exception ex){
            String err = "Error: " + ex.getMessage();
            this.logger.error(err);
        }
    }

    @Async
    @PostMapping("/create-log")
    public CompletableFuture<ResponseEntity<LogResponseDTO>> createLog(@RequestBody LogRequestDTO logEntry){
        try{
            this.logger.info("endpoint /create-log started.");
            return _logService.createLog(logEntry)
                    .thenApply(saved -> ResponseEntity.status(201).body(saved));
        }
        catch(Exception ex){
            this.logger.error(ex.getMessage());
            throw ex;
        }
    }
}
