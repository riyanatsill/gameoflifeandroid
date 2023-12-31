package com.example.gameoflife1.model;

import java.io.Serializable;

public class TransactionModel implements Serializable {
    private String game;
    private String username;
    private String id;
    private String product;
    private String payment;
    private String status;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    private String key;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getProduct(){
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}
