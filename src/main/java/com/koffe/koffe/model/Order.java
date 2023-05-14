package com.koffe.koffe.model;

import java.sql.Timestamp;

public class Order {
    private int orderId;
    private int userId;
    private int addressId;
    private int total;
    private boolean status;
    private Timestamp date;

    public Order() {
    }

    public Order(int userId, int addressId, int total) {
        this.userId = userId;
        this.addressId = addressId;
        this.total = total;
    }

    public Order(int orderId, int userId, int addressId, int total, boolean status, Timestamp date) {
        this.orderId = orderId;
        this.userId = userId;
        this.addressId = addressId;
        this.total = total;
        this.status = status;
        this.date = date;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
