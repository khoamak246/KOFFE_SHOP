package com.koffe.koffe.model;

public class OrderItems {
    private int orderItemsId;
    private int orderId;
    private int productId;
    private int sizeId;
    private int price;
    private int quantity;

    public OrderItems() {
    }


    public OrderItems(int orderId, int productId, int sizeId, int price, int quantity) {
        this.orderId = orderId;
        this.productId = productId;
        this.sizeId = sizeId;
        this.price = price;
        this.quantity = quantity;
    }

    public OrderItems(int orderItemsId, int orderId, int productId, int sizeId, int price, int quantity) {
        this.orderItemsId = orderItemsId;
        this.orderId = orderId;
        this.productId = productId;
        this.sizeId = sizeId;
        this.price = price;
        this.quantity = quantity;
    }

    public int getOrderItemsId() {
        return orderItemsId;
    }

    public void setOrderItemsId(int orderItemsId) {
        this.orderItemsId = orderItemsId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
