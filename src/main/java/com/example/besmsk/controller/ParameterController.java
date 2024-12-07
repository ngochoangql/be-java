package com.example.besmsk.controller;

import com.example.besmsk.model.Parameter;
import com.example.besmsk.service.ParameterService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/parameters")
public class ParameterController {

    @Autowired
    private ParameterService parameterService;

    @PostMapping
    public List<Parameter> getParameters(@RequestBody String requestBody) throws Exception {
        JSONObject jsonObject = new JSONObject(requestBody);

        String startDateString = jsonObject.getString("startDate");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date startDate = dateFormat.parse(startDateString);

        String endDateString = jsonObject.getString("endDate");
        Date endDate = dateFormat.parse(endDateString);

        String productId = jsonObject.getString("productId");

        return parameterService.getParametersBetween(productId,startDate, endDate);
    }

}
