package com.example.besmsk.repository;

import com.example.besmsk.model.Device;
import com.example.besmsk.model.Parameter;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface ParameterRepository  extends MongoRepository<Parameter, String> {

    List<Parameter> findParameterByProductId(String productId);
    List<Parameter> findAllByProductIdAndCreatedAtBetween(String productId, Date startOfToday, Date endOfToday);
}
