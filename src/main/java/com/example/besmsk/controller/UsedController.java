package com.example.besmsk.controller;

import com.example.besmsk.model.Used;
import com.example.besmsk.service.UsedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/useds")
public class UsedController {

    @Autowired
    private UsedService usedService;

    @GetMapping("/today/{productId}")
    public Used getUsedByProductId(@PathVariable String productId) {
        return usedService.getUsedDataForToday(productId, new Date());
    }

    @GetMapping("/{productId}")
    public List<Used> getUsedsByProductId(@PathVariable String productId) {
        return usedService.getUsedsByProduct(productId);
    }
}
