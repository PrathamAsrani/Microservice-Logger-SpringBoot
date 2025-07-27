package com.logger.api.model;

import com.logger.api.enums.LogEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Map;

@Document(collection = "logs")
public class LogEntry {
    @Id
    private String Id;
    public final Instant createdAt;
    private Map<String, Object> payload;
    private LogEnum level;

    public LogEntry(){
        this.createdAt = Instant.now();
    }

    public LogEntry(Map<String, Object> payload, LogEnum level){
        this.createdAt = Instant.now();
        this.payload = payload;
        this.level = level;
    }

    public String getId() {
        return Id;
    }

    public Instant getTimeStamps() {
        return createdAt;
    }

    public Map<String, Object> getPayload() {
        return payload;
    }

    public void setPayload(Map<String, Object> payload) {
        this.payload = payload;
    }

    public LogEnum getLevel() {
        return level;
    }
}
