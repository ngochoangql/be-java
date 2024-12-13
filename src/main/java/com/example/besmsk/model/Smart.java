package com.example.besmsk.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "smarts")
public class Smart {

    @Id
    private String id;
    private String productId;
    private String type;
    private double value;
    private String condition;
    private String relays;
    private boolean action;
    private boolean status;
    private Date date;

    public Smart() {
    }

    public Smart(String id, String productId, String type, double value, String condition, String relays, boolean action, boolean status, Date date) {
        this.id = id;
        this.productId = productId;
        this.type = type;
        this.value = value;
        this.condition = condition;
        this.relays = relays;
        this.action = action;
        this.status = status;
        this.date = date;
    }

    public Smart(String productId, String type, double value, String condition, String relays, boolean action, boolean status, Date date) {
        this.productId = productId;
        this.type = type;
        this.value = value;
        this.condition = condition;
        this.relays = relays;
        this.action = action;
        this.status = status;
        this.date = date;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getRelays() {
        return relays;
    }

    public void setRelays(String relays) {
        this.relays = relays;
    }

    public boolean isAction() {
        return action;
    }

    public void setAction(boolean action) {
        this.action = action;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
