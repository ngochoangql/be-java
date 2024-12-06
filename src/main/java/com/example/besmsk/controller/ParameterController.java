package com.example.besmsk.controller;

import com.example.besmsk.service.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/data")
public class ParameterController {

    @Autowired
    private ParameterService parameterService;

//    @GetMapping("/{productId}")
//    public List<P> getParameters(@PathVariable("productId") String productId) {
//        r`
//    }
}
