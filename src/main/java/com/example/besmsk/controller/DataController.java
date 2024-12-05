package com.example.besmsk.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/data")
public class DataController {
    @PostMapping
    public void data(@RequestBody List<Object> data) {
        System.out.println(data.toString());
    }
}
