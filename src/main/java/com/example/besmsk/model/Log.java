package com.example.besmsk.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "logs")
public class Log {
    @Id
    private String id;
    private String productId;
    private String deviceId;
    private String content;
    private Date createAt;
    public Log() {
    }

    public Log(String productId, String deviceId, String content, Date createAt) {
        this.productId = productId;
        this.deviceId = deviceId;
        this.content = content;
        this.createAt = createAt;
    }

    public Log(String id, String productId, String deviceId, String content, Date createAt) {
        this.id = id;
        this.productId = productId;
        this.deviceId = deviceId;
        this.content = content;
        this.createAt = createAt;
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

    public String getdeviceId() {
        return deviceId;
    }

    public void setdeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
}