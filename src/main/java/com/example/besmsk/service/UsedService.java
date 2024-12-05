package com.example.besmsk.service;

import com.example.besmsk.model.Used;
import com.example.besmsk.repository.UsedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsedService {

    @Autowired
    private UsedRepository usedRepository;

    public Used createUsed(Used used) {
        return usedRepository.save(used);
    }

    public List<Used> getUsedsByProduct(String productId) {
        return usedRepository.findUsedsByProductId(productId);
    }
}
