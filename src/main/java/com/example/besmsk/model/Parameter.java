package com.example.besmsk.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "parameters")
public class Parameter {
    @Id
    private String id;
    private String productId;
    private String deviceId;
    private double current;
    private double voltage;
    private double activePower;
    private double apparentPower;
    private Date createdAt;
}
