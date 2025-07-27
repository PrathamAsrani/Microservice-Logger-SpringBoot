package com.logger.api.service;

import com.logger.api.common.AppLogger;
import com.logger.api.exception.LogCreationException;
import com.logger.api.model.LogEntry;
import com.logger.api.repository.LogRepo;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.logger.api.model.dtos.LogRequestDTO;
import com.logger.api.model.dtos.LogResponseDTO;
import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.CompletableFuture;
import com.logger.api.enums.LogEnum;
import org.slf4j.Logger;

@Service
public class LogService {
    private final LogRepo _logRepo;
    private final Logger logger = AppLogger.getLogger(LogService.class);

    @Autowired
    public LogService(LogRepo logRepo){
        _logRepo=logRepo;
    }

    @Async
    public CompletableFuture<LogResponseDTO> createLog(LogRequestDTO logEntry){
        try{
            this.logger.info("creating log-level as per dto");
            LogEnum logLevel = LogEnum.valueOf(logEntry.level.toUpperCase());
            this.logger.info("creating log-entry");
            LogEntry entry =_logRepo.save(new LogEntry(logEntry.payload, logLevel));
            this.logger.info("saved in the database, returing the log-entry");
            return CompletableFuture.completedFuture(new LogResponseDTO(entry.getId(), entry.createdAt, entry.getPayload(), entry.getLevel()));
        }
        catch(Exception ex){
            this.logger.error("Error in saving the log entry" + ex.getMessage());
            throw new LogCreationException(ex.getMessage());
        }
    }
}
