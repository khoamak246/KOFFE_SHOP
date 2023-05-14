package com.koffe.koffe.model;

public class OrderItemsDetail {
    private int orderItemsId;
    private String productName;
    private String sizeDetail;
    private int quantity;
    private int total;
    private String productAvatar;
    public OrderItemsDetail() {
    }

    public OrderItemsDetail(int orderItemsId, String productName, String sizeDetail, int quantity, int total, String productAvatar) {
        this.orderItemsId = orderItemsId;
        this.productName = productName;
        this.sizeDetail = sizeDetail;
        this.quantity = quantity;
        this.total = total;
        this.productAvatar = productAvatar;
    }

    public int getOrderItemsId() {
        return orderItemsId;
    }

    public void setOrderItemsId(int orderItemsId) {
        this.orderItemsId = orderItemsId;
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
    public String getProductAvatar() {
        return productAvatar;
    }
    public void setProductAvatar(String productAvatar) {
        this.productAvatar = productAvatar;
    }
}
