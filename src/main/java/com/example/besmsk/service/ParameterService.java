package com.example.besmsk.service;

import com.example.besmsk.model.Parameter;
import com.example.besmsk.repository.ParameterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParameterService {

    @Autowired
    private ParameterRepository parameterRepository;


    public Parameter createParameter(Parameter parameter) {
        return parameterRepository.save(parameter);
    }

    public List<Parameter> getAllParametersByProductId(String productId) {
        return parameterRepository.findParameterByProductId(productId);
    }
}
