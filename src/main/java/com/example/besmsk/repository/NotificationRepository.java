package com.example.besmsk.repository;

import com.example.besmsk.model.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NotificationRepository extends MongoRepository<Notification, String> {
    List<Notification> findAllByProductId(String productId);
}
