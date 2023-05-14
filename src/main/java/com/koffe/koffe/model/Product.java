package com.koffe.koffe.model;

public class Product {
    private int productId;
    private String productName;
    private String productDescription;
    private int sizeId;
    private int price;
    private String productAvatar;
    private boolean status;

    public Product() {
    }

    public Product(String productName, String productDescription, int sizeId, int price, String productAvatar) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.sizeId = sizeId;
        this.price = price;
        this.productAvatar = productAvatar;
    }

    public Product(int productId, String productName, String productDescription, int sizeId, int price, String productAvatar, boolean status) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.sizeId = sizeId;
        this.price = price;
        this.productAvatar = productAvatar;
        this.status = status;
    }

    public String getProductAvatar() {
        return productAvatar;
    }
    public void setProductAvatar(String productAvatar) {
        this.productAvatar = productAvatar;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getProductDescription() {
        return productDescription;
    }
    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
