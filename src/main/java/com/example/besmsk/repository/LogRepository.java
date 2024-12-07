package com.example.besmsk.repository;

import com.example.besmsk.model.Log;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LogRepository extends MongoRepository<Log, String> {
    List<Log> findByProductId(String productId);
    void deleteLogsByProductId(String productId);
}
