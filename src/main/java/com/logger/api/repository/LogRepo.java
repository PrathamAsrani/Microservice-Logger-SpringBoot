package com.logger.api.repository;

import com.logger.api.model.LogEntry;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepo extends MongoRepository<LogEntry , String> {
}
