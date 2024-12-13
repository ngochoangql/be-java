package com.example.besmsk.controller;

import com.example.besmsk.model.Device;
import com.example.besmsk.model.Relay;
import com.example.besmsk.service.DeviceService;
import com.example.besmsk.service.RelayService;
import com.example.besmsk.util.WebSocketSessionManager;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/relays")
public class RelayController {
    @Autowired
    private RelayService relayService;
    @Autowired
    private DeviceService deviceService;


    @GetMapping("/{productId}")
    public List<Relay> getRelaysByDeviceId(@PathVariable String productId) {
        Device device = deviceService.getDeviceByProductId(productId);
        System.out.println(device.getProductId());
        return relayService.getRelaysByDeviceId(device.getId());
    }

    @PatchMapping("/update")
    public ResponseEntity<Object> updateRelay(@RequestBody Relay relay) {
        Device device = deviceService.getDeviceById(relay.getDeviceId());
        WebSocketSessionManager.sendMessageToProduct(device.getProductId(), "updateRelay",
                "{\"event\":\"updateRelay\",\"relayNumber\":" + relay.getRelayNumber() + ",\"id\":\"" + relay.getId() + "\",\"status\":" + relay.isStatus() + "}", null);
        System.out.println(relay.isStatus());
        handleDeviceUpdate(device,relay.isStatus(),relay.getRelayNumber());
        return ResponseEntity.ok().body(Map.of("code", 200));
    }

    @GetMapping("/update/param")
    public ResponseEntity<Object> updateRelay(@RequestParam String productId,
                                              @RequestParam int relayNumber,
                                              @RequestParam boolean status) {

        // Get the device by productId
        Device device = deviceService.getDeviceByProductId(productId);
        System.out.println("Product ID: " + productId);

        // Get the relay by deviceId and relayNumber
        Relay relay = relayService.getRelayByDeviceIdAndRelayNumber(device.getId(), relayNumber);

        // Send a WebSocket message to update the relay status on the product
        WebSocketSessionManager.sendMessageToProduct(device.getProductId(), "updateRelay",
                "{\"event\":\"updateRelay\",\"relayNumber\":" + relayNumber +
                        ",\"id\":\"" + relay.getId() + "\",\"status\":" + status + "}", null);

        // Update the device status
        handleDeviceUpdate(device, status, relayNumber);

        // Print the updated relay status
        System.out.println("Updated Relay Status: " + status    );

        // Return a success response
        return ResponseEntity.ok().body(Map.of("code", 200));
    }
    @GetMapping("/init/{productId}")
    public void updateInitDevice(@PathVariable String productId) {

        Device device = deviceService.getDeviceByProductId(productId);
        List<Relay> relays = relayService.getRelaysByDeviceId(device.getId());
        for (Relay relay : relays) {
            WebSocketSessionManager.sendMessageToProduct(productId, "updateRelay",
                    "{\"event\":\"updateRelay\",\"relayNumber\":"+relay.getRelayNumber()+",\"id\":\"" + relay.getId() + "\",\"status\":false}", null);
        }
        relayService.updateStatusRelayById(productId,1,false);
        relayService.updateStatusRelayById(productId,2,false);
        relayService.updateStatusRelayById(productId,3,false);
        device.setStatus(false);
        deviceService.updateDevice(device);
        WebSocketSessionManager.sendMessageToProduct(device.getProductId(), "updateDevice",
                "{\"event\":\"updateDevice\",\"productId\":\""+device.getProductId()+"\",\"status\":false}", null);

    }

    public void handleDeviceUpdate(Device device,boolean status, int num) {
        if (status) {
            device.setStatus(true);
            deviceService.updateDevice(device);
            WebSocketSessionManager.sendMessageToProduct(device.getProductId(), "updateDevice",
                    "{\"event\":\"updateDevice\",\"productId\":\""+device.getProductId()+"\",\"status\":true}", null);

            return;
        }
        List<Relay> relays = relayService.getRelaysByDeviceId(device.getId());
        for (Relay relay : relays) {
            if (relay.isStatus() && relay.getRelayNumber() != num) {
                device.setStatus(true);
                deviceService.updateDevice(device);
                WebSocketSessionManager.sendMessageToProduct(device.getProductId(), "updateDevice",
                        "{\"event\":\"updateDevice\",\"productId\":\""+device.getProductId()+"\",\"status\":true}", null);

                return;
            }
        }
        device.setStatus(false);
        deviceService.updateDevice(device);
        WebSocketSessionManager.sendMessageToProduct(device.getProductId(), "updateDevice",
                "{\"event\":\"updateDevice\",\"productId\":\""+device.getProductId()+"\",\"status\":false}", null);
    }
}