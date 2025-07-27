package com.logger.api.model.dtos;

import java.util.Map;
import java.time.Instant;
import com.logger.api.enums.LogEnum;


public class LogResponseDTO {
    public String id;
    public Instant createdAt;
    public Map<String, Object> payload;
    public LogEnum logLevel;

    public LogResponseDTO(String id, Instant createdAt, Map<String, Object> payload, LogEnum logLevel){
        this.id = id;
        this.logLevel = logLevel;
        this.payload = payload;
        this.createdAt = createdAt;
    }
}
