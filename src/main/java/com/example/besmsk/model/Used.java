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

    public Used() {
    }

    public Used(String id, String productId, String deviceId, double kwh, double hours, Date createdAt) {
        this.id = id;
        this.productId = productId;
        this.deviceId = deviceId;
        this.kwh = kwh;
        this.hours = hours;
        this.createdAt = createdAt;
    }

    public Used(String productId, String deviceId, double kwh, double hours, Date createdAt) {
        this.productId = productId;
        this.deviceId = deviceId;
        this.kwh = kwh;
        this.hours = hours;
        this.createdAt = createdAt;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public double getKwh() {
        return kwh;
    }

    public void setKwh(double kwh) {
        this.kwh = kwh;
    }

    public double getHours() {
        return hours;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
