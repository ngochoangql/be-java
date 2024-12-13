package com.example.besmsk.repository;

import com.example.besmsk.model.Smart;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SmartRepository extends MongoRepository<Smart, String> {
    List<Smart> findAllByProductIdAndStatus(String productId, boolean status);
    List<Smart> findSmartsByProductId(String productId);

}
