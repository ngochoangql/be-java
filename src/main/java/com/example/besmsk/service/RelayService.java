package com.example.besmsk.service;

import com.example.besmsk.model.Relay;
import com.example.besmsk.repository.RelayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelayService {


    @Autowired
    private RelayRepository relayRepository;

    public Relay createRelay(Relay relay) {
        return relayRepository.save(relay);
    }

    public boolean updateStatusRelayById(String id,boolean status) {
        return relayRepository.findById(id)
                .map(relay -> {
                    relay.setStatus(status);
                    relayRepository.save(relay);
                    return true;
                })
                .orElse(false);
    }

    public List<Relay> getRelaysByDeviceId(String deviceId) {
        return relayRepository.findByDeviceId(deviceId);
    }

    public void deleteRelays(String deviceId) {
        relayRepository.deleteRelaysByDeviceId(deviceId);
    }
}
