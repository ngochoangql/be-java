package com.example.besmsk.repository;

import com.example.besmsk.model.Device;
import com.example.besmsk.model.Used;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UsedRepository  extends MongoRepository<Used, String> {

    List<Used> findUsedsByProductId(String productId);
}
