package com.example.besmsk.service;

import com.example.besmsk.model.Device;
import com.example.besmsk.model.Relay;
import com.example.besmsk.repository.DeviceRepository;
import com.example.besmsk.repository.RelayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelayService {


    @Autowired
    private RelayRepository relayRepository;
    @Autowired
    private DeviceRepository deviceRepository;


    public Relay createRelay(Relay relay) {
        return relayRepository.save(relay);
    }

    public boolean updateStatusRelayById(String productId, int relayNumber, boolean status) {
        Device device = deviceRepository.findByProductId(productId);
        List<Relay> relays = relayRepository.findByDeviceId(device.getId());
        for (Relay relay : relays) {
            if (relay.getRelayNumber() == relayNumber) {
                relay.setStatus(status);
                relayRepository.save(relay);
                return true;
            }
        }
        return false;
    }

    public List<Relay> getRelaysByDeviceId(String deviceId) {
        return relayRepository.findByDeviceId(deviceId);
    }

    public Relay getRelayByDeviceIdAndRelayNumber(String deviceId, int relayNumber) {
        return relayRepository.getRelayByDeviceIdAndRelayNumber(deviceId,relayNumber);
    }


    public void deleteRelays(String deviceId) {
        relayRepository.deleteRelaysByDeviceId(deviceId);
    }

}
