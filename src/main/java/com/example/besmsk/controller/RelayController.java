package com.example.besmsk.controller;

import com.example.besmsk.service.DeviceService;
import com.example.besmsk.service.RelayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/relays")
public class RelayController {
    @Autowired
    private RelayService relayService;
    @Autowired
    private DeviceService deviceService;

}
