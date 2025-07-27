package com.logger.api.service;

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

@Service
public class LogService {
    private final LogRepo _logRepo;

    @Autowired
    public LogService(LogRepo logRepo){
        _logRepo=logRepo;
    }

    @Async
    public CompletableFuture<LogResponseDTO> createLog(LogRequestDTO logEntry){
        try{
            LogEnum logLevel = LogEnum.valueOf(logEntry.level.toUpperCase());
            LogEntry entry =_logRepo.save(new LogEntry(logEntry.payload, logLevel));
            return CompletableFuture.completedFuture(new LogResponseDTO(entry.getId(), entry.createdAt, entry.getPayload(), entry.getLevel()));
        }
        catch(Exception ex){
            throw new LogCreationException(ex.getMessage());
        }
    }
}
