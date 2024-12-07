package com.example.besmsk.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "parameters")
public class Parameter {
    @Id
    private String id;
    private String productId;
    private double current;
    private double voltage;
    private double activePower;
    private double apparentPower;
    private Date createdAt;

    public Parameter() {
    }

    public Parameter(String productId,double current, double voltage, double activePower, double apparentPower, Date createdAt) {
        this.productId = productId;

        this.current = current;
        this.voltage = voltage;
        this.activePower = activePower;
        this.apparentPower = apparentPower;
        this.createdAt = createdAt;
    }

    public Parameter(String id, String productId, String deviceId, double current, double voltage, double activePower, double apparentPower, Date createdAt) {
        this.id = id;
        this.productId = productId;
        this.current = current;
        this.voltage = voltage;
        this.activePower = activePower;
        this.apparentPower = apparentPower;
        this.createdAt = createdAt;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public double getCurrent() {
        return current;
    }

    public void setCurrent(double current) {
        this.current = current;
    }

    public double getVoltage() {
        return voltage;
    }

    public void setVoltage(double voltage) {
        this.voltage = voltage;
    }

    public double getActivePower() {
        return activePower;
    }

    public void setActivePower(double activePower) {
        this.activePower = activePower;
    }

    public double getApparentPower() {
        return apparentPower;
    }

    public void setApparentPower(double apparentPower) {
        this.apparentPower = apparentPower;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
