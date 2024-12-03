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
        WebSocketSessionManager.sendMessageToProduct(device.getProductId(),"updateRelay",
                "{\"event\":\"updateRelay\",\"relayNumber\":"+relay.getRelayNumber()+",\"id\":\""+relay.getId()+"\",\"status\":"+relay.isStatus()+"}",null);
        System.out.println(relay.isStatus());
        return ResponseEntity.ok().body(Map.of("code", 200));
    }
}
