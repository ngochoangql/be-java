package com.example.besmsk.repository;

import com.example.besmsk.model.Schedule;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ScheduleRepository extends MongoRepository<Schedule, String> {

    List<Schedule> getSchedulesByProductId(String productId);
    Schedule getScheduleById(String id);
}
