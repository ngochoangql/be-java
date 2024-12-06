package com.example.besmsk.controller;


import com.example.besmsk.model.Device;
import com.example.besmsk.model.Parameter;
import com.example.besmsk.model.Used;
import com.example.besmsk.service.DeviceService;
import com.example.besmsk.service.ParameterService;
import com.example.besmsk.service.UsedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/data")
public class DataController {

    @Autowired
    private ParameterService parameterService;

    @Autowired
    private UsedService usedService;
    @Autowired
    private DeviceService deviceService;

    @PostMapping("/{productId}")
    public void data(@RequestBody List<Parameter> parameters, @PathVariable String productId) {
        System.out.println(parameters.get(0).getActivePower());
        parameters.get(parameters.size()-1).setCreatedAt(new Date());
        parameterService.createParameter(parameters.get(parameters.size()-1));
        double totalKWhUsed = parameters.get(parameters.size()-1).getActivePower();
        Date now = new Date();
        Used used = usedService.getUsedDataForToday(productId,now);
        Device device = deviceService.getDeviceByProductId(productId);
        if (used == null){
            Used createdUsed = new Used(productId,device.getId(),totalKWhUsed,1,now);
            usedService.createUsed(createdUsed);
        }else{
            used.setKwh(used.getKwh()+totalKWhUsed);
            used.setHours(used.getHours()+1);

            usedService.updateUsed(used);
        }

    }
}
