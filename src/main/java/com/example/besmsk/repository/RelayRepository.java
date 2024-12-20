package com.example.besmsk.repository;

import com.example.besmsk.model.Relay;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RelayRepository extends MongoRepository<Relay, String> {
    List<Relay> findByDeviceId(String deviceId);
    void deleteRelaysByDeviceId(String deviceId);
    Relay getRelayByDeviceIdAndRelayNumber(String deviceId, int relayNumber);
}
