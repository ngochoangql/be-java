package com.example.besmsk.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "notifications")
public class Notification {
    @Id
    private String id;
    private String productId;
    private String type;
    private String content;
    private Date createAt;

    // Constructor


    public Notification() {
    }

    public Notification(String productId, String type, String content, Date createAt) {
        this.productId = productId;
        this.type = type;
        this.content = content;
        this.createAt = createAt;
    }

    public Notification(String id, String productId, String type, String content, Date createAt) {
        this.id = id;
        this.productId = productId;
        this.type = type;
        this.content = content;
        this.createAt = createAt;
    }

    // Getters and Setters
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // Method to convert Notification to String (for simplicity)
    @Override
    public String toString() {
        return "Notification [productId=" + productId + ", content=" + content + ", createAt=" + createAt + "]";
    }
}

