package com.example.besmsk.service;

import com.example.besmsk.model.Device;
import com.example.besmsk.model.Log;
import com.example.besmsk.model.Relay;
import com.example.besmsk.repository.DeviceRepository;
import com.example.besmsk.repository.LogRepository;
import com.example.besmsk.repository.RelayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogService {


    @Autowired
    private LogRepository logRepository;

    public Log createLog(Log log) {
        return logRepository.save(log);
    }

    public List<Log> getLogsByProductId(String productId) {
        return logRepository.findByProductId(productId);
    }

    public void deleteLogs(String productId) {
        logRepository.deleteLogsByProductId(productId);
    }
    public List<Log> getAllLogs() {
        return logRepository.findAll();
    }
}