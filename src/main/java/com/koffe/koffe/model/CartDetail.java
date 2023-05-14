package com.koffe.koffe.model;

import java.sql.Timestamp;

public class CartDetail {
    private int cartId;
    private int userId;
    private int productId;
    private int sizeId;
    private String productName;
    private String sizeDetail;
    private int price;
    private int quantity;
    private int total;
    private String avatar;
    private Timestamp date;


    public CartDetail() {
    }

    public CartDetail(int cartId, int userId, int productId, int sizeId, String productName, String sizeDetail, int price, int quantity, int total, String avatar, Timestamp date) {
        this.cartId = cartId;
        this.userId = userId;
        this.productId = productId;
        this.sizeId = sizeId;
        this.productName = productName;
        this.sizeDetail = sizeDetail;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
        this.avatar = avatar;
        this.date = date;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getSizeId() {
        return sizeId;
    }

    public void setSizeId(int sizeId) {
        this.sizeId = sizeId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSizeDetail() {
        return sizeDetail;
    }

    public void setSizeDetail(String sizeDetail) {
        this.sizeDetail = sizeDetail;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
