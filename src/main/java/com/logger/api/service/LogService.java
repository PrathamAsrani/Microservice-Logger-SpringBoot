package com.logger.api.service;

import com.logger.api.exception.LogCreationException;
import com.logger.api.model.LogEntry;
import com.logger.api.repository.LogRepo;
import org.springframework.stereotype.Service;

@Service
public class LogService {
    private final LogRepo _logRepo;

    public LogService(LogRepo logRepo){
        _logRepo=logRepo;
    }

    public LogEntry createLog(LogEntry logEntry){
        try{
            return _logRepo.save(logEntry);
        }
        catch(Exception ex){
            throw new LogCreationException(ex.getMessage());
        }
    }
}
