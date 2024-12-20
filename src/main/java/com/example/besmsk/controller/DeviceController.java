package com.example.besmsk.controller;

import com.example.besmsk.model.Device;
import com.example.besmsk.model.Relay;
import com.example.besmsk.service.DeviceService;
import com.example.besmsk.service.RelayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/devices")
@Api(value = "Device Controller", description = "Operations related to Devices")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;
    @Autowired
    private RelayService relayService;

    @GetMapping
    @ApiOperation(value = "Get all devices", notes = "This API retrieves a list of all devices.")
    public List<Device> getAllDevices() {
        return deviceService.getAllDevices();

    }

    @PostMapping
    public Device createDevice(@RequestBody Device device) {
        Device device1 = deviceService.getDeviceByProductId(device.getProductId());
        if (device1 == null) {
            Device newDevice = deviceService.createDevice(device);
            for (int i = 1; i <= device.getNumOfRelay(); i++) {
                Relay relay = relayService.createRelay(new Relay(
                        newDevice.getId(),
                        i,
                        "Relay " + i,
                        false
                ));
            }

            return deviceService.createDevice(device);
        }
        return device;
    }

    @DeleteMapping("/{deviceId}")
    public void deleteDevice(@PathVariable String deviceId) {
        deviceService.deleteDevice(deviceId);
        relayService.deleteRelays(deviceId);
    }

    @GetMapping("/{productId}")
    public Device getDeviceByProductId(@PathVariable String productId) {
        return deviceService.getDeviceByProductId(productId);
    }
}