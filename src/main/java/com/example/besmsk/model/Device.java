package com.example.besmsk.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "devices")
public class Device {
    @Id
    private String id;
    private String productId;
    private String deviceMqtt;
    private String deviceHttp;
    private int numOfRelay;
    private String name;
    private String type;
    private boolean status;
    private Date createdAt;

    public Device() {
    }

    public Device(
            String productId,
            String id,
            String deviceMqtt,
            String deviceHttp,
            int numOfRelay,
            String name,
            String type,
            boolean status,
            Date createdAt) {
        this.productId = productId;
        this.id = id;
        this.deviceMqtt = deviceMqtt;
        this.deviceHttp = deviceHttp;
        this.numOfRelay = numOfRelay;
        this.name = name;
        this.type = type;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Device(
            String productId,
            String deviceMqtt,
            String deviceHttp,
            int numOfRelay,
            String name,
            String type,
            boolean status,
            Date createdAt) {
        this.productId = productId;
        this.deviceMqtt = deviceMqtt;
        this.deviceHttp = deviceHttp;
        this.numOfRelay = numOfRelay;
        this.name = name;
        this.type = type;
        this.status = status;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getDeviceMqtt() {
        return deviceMqtt;
    }

    public void setDeviceMqtt(String deviceMqtt) {
        this.deviceMqtt = deviceMqtt;
    }

    public String getDeviceHttp() {
        return deviceHttp;
    }

    public void setDeviceHttp(String deviceHttp) {
        this.deviceHttp = deviceHttp;
    }

    public int getNumOfRelay() {
        return numOfRelay;
    }

    public void setNumOfRelay(int numOfRelay) {
        this.numOfRelay = numOfRelay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
