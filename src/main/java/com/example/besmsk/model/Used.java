package com.example.besmsk.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "useds")
public class Used {
    @Id
    private String id;
    private String productId;
    private String deviceId;
    private double kwh;
    private double hours;
    private Date createdAt;
}
