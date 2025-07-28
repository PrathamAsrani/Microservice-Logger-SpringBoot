package com.logger.api.service;

import java.time.Instant;
import com.logger.api.common.AppLogger;
import com.logger.api.exception.LogCreationException;
import com.logger.api.model.LogEntry;
import com.logger.api.model.dtos.LogFilterDTO;
import com.logger.api.repository.LogRepo;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.logger.api.model.dtos.LogRequestDTO;
import com.logger.api.model.dtos.LogResponseDTO;
import org.springframework.scheduling.annotation.Async;
import java.time.ZoneOffset;
import java.util.concurrent.CompletableFuture;
import com.logger.api.enums.LogEnum;
import org.slf4j.Logger;
import java.util.List;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Criteria;

@Service
public class LogService {
    private final LogRepo _logRepo;
    private final Logger logger = AppLogger.getLogger(LogService.class);
    private final MongoTemplate _mongoTemplate;

    @Autowired
    public LogService(LogRepo logRepo, MongoTemplate mongoTemplate){
        _logRepo=logRepo;
        _mongoTemplate = mongoTemplate;
    }

    @Async
    public CompletableFuture<LogResponseDTO> createLog(LogRequestDTO logEntry){
        try{
            this.logger.info("creating log-level as per dto");
            LogEnum logLevel = LogEnum.valueOf(logEntry.level.toUpperCase());
            this.logger.info("creating log-entry");
            LogEntry entry =_logRepo.save(new LogEntry(logEntry.payload, logLevel));
            this.logger.info("saved in the database, returing the log-entry");
            return CompletableFuture.completedFuture(new LogResponseDTO(entry.getId(), entry.getTimeStamps(), entry.getPayload(), entry.getLevel()));
        }
        catch(Exception ex){
            this.logger.error("Error in saving the log entry" + ex.getMessage());
            throw new LogCreationException(ex.getMessage());
        }
    }

    @Async
    public CompletableFuture<List<LogResponseDTO>> getFilteredLogs(LogFilterDTO filters){
        Query q = new Query();

        Instant from = filters.startDate != null
                ? filters.startDate.atStartOfDay().toInstant(ZoneOffset.UTC)
                : Instant.EPOCH;
        Instant to = filters.endDate != null
                ? filters.endDate.plusDays(1).atStartOfDay().toInstant(ZoneOffset.UTC)
                : Instant.now().plusSeconds(86400);
        q.addCriteria(Criteria.where("createdAt").gte(from).lte(to));

        if(filters.level != null){
            q.addCriteria(Criteria.where("level").is(filters.level));
        }

        List<LogEntry> res = this._mongoTemplate.find(q, LogEntry.class);
        return CompletableFuture.completedFuture(
                res.stream()
                        .map(it -> new LogResponseDTO(it.getId(), it.getTimeStamps(), it.getPayload(), it.getLevel()))
                        .toList()
        );
    }
}
