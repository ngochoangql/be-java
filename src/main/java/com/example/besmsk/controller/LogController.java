package com.example.besmsk.controller;


import com.example.besmsk.model.Log;
import com.example.besmsk.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/logs")
public class LogController {

    @Autowired
    private LogService logService;

    
    @GetMapping
    public List<Log> getLogs() {
        return logService.getAllLogs();
    }


    @GetMapping("/{productId}")
    public List<Log> getLogsByProductId(@PathVariable String productId) {
        return logService.getLogsByProductId(productId);
    }

    @DeleteMapping("/{productId}")
    public void deleteLogs(@PathVariable String productId) {
        logService.deleteLogs(productId);
    }

}
