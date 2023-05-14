package com.koffe.koffe.model;

import java.sql.Timestamp;

public class Cart {
    private int cartId;
    private int userId;
    private int productId;
    private int sizeId;
    private int price;
    private int total;
    private int quantity;

    private Timestamp date;

    public Cart() {
    }

    public Cart(int userId, int productId, int sizeId, int quantity) {
        this.userId = userId;
        this.productId = productId;
        this.sizeId = sizeId;
        this.quantity = quantity;
    }

    public Cart(int cartId, int userId, int productId, int sizeId, int price, int total, int quantity, Timestamp date) {
        this.cartId = cartId;
        this.userId = userId;
        this.productId = productId;
        this.sizeId = sizeId;
        this.price = price;
        this.total = total;
        this.quantity = quantity;
        this.date = date;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartId=" + cartId +
                ", userId=" + userId +
                ", productId=" + productId +
                ", sizeId=" + sizeId +
                ", price=" + price +
                ", total=" + total +
                ", quantity=" + quantity +
                ", date=" + date +
                '}';
    }
}
