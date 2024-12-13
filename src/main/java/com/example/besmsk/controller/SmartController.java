package com.example.besmsk.controller;


import com.example.besmsk.model.Smart;
import com.example.besmsk.service.SmartService;
import com.example.besmsk.util.WebSocketSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/smarts")
public class SmartController {

    @Autowired
    private SmartService smartService;

    @PostMapping
    public Smart createSmart(@RequestBody Smart smart) {
        return smartService.createSmart(smart);
    }

    @GetMapping("/{productId}")
    public List<Smart> getSmartsByProductId(@PathVariable String productId) {
        return smartService.getSmartsByProductId(productId);
    }
    @GetMapping("/active/{productId}")
    public List<Smart> getSmartsByProductIdAndStatus(@PathVariable String productId) {

        return smartService.getSmartsByProductIdAndStatus(productId,true);
    }

    @PostMapping("/update")
    public Smart updateSmart(@RequestParam String productId,
                             @RequestParam String id,
                             @RequestParam boolean status) {

        WebSocketSessionManager.sendMessageToProduct(productId,"notifySmart",
                "{\"event\":\"notifySmart\",\"id\":\""+ id+",\"status\":"+Boolean.toString(status)+"}",null);
        return smartService.updateSmart(id,status);
    }
}
