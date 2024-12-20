package com.example.besmsk.repository;

import com.example.besmsk.model.Device;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DeviceRepository extends MongoRepository<Device, String> {

    Device getDeviceById(String id);
    Device findByProductId(String productId);
}
