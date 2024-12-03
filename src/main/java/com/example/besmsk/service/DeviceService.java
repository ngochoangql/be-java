package com.example.besmsk.service;

import com.example.besmsk.model.Device;
import com.example.besmsk.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    public Device getDeviceByProductId(String productId) {
        return deviceRepository.getDeviceByProductId(productId);
    }

    public Device getDeviceById(String id) {
        return deviceRepository.getDeviceById(id);
    }

    public Device createDevice(Device device) {
        // Thêm thời gian tạo vào lúc tạo device
        device.setCreatedAt(new Date());
        return deviceRepository.save(device);
    }

    public void deleteDevice(String id) {
        if (!deviceRepository.existsById(id)) {
            throw new IllegalArgumentException("Device with ID " + id + " does not exist");
        }
        deviceRepository.deleteById(id);
    }
}
