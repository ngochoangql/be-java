package com.example.besmsk.service;


import com.example.besmsk.model.Smart;
import com.example.besmsk.repository.SmartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SmartService {

    @Autowired
    private SmartRepository smartRepository;

    public Smart createSmart(Smart smart) {
        smart.setDate(new Date());
        return smartRepository.save(smart);
    }

    public List<Smart> getSmartsByProductId(String productId) {
        return smartRepository.findSmartsByProductId(productId);
    }

    public List<Smart> getSmartsByProductIdAndStatus(String productId, boolean status) {
        return smartRepository.findAllByProductIdAndStatus(productId, status);
    }

    public void deleteTask(String id) {
        smartRepository.deleteById(id);
    }

    public Smart updateSmart(String id,boolean status) {
        Smart smart =  smartRepository.findById(id).orElse(null);
        if (smart != null) {
            smart.setStatus(status);
            smartRepository.save(smart);
            return smart;
        }
        return null;
    }
}
