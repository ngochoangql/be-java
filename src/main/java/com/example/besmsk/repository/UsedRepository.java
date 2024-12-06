package com.example.besmsk.repository;

import com.example.besmsk.model.Device;
import com.example.besmsk.model.Used;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface UsedRepository  extends MongoRepository<Used, String> {

    List<Used> findUsedsByProductId(String productId);
    Used findUsedByProductIdAndCreatedAt(String productId, Date createdAt);
    Used findByProductIdAndCreatedAtBetween(String productId,Date from, Date to);
}
