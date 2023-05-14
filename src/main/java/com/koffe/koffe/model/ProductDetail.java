package com.koffe.koffe.model;

public class ProductDetail {
    private int productId;
    private String productName;
    private String productDescription;
    private String avatar;
    private int priceS;
    private int priceM;
    private int priceL;

    public ProductDetail() {
    }

    public ProductDetail(String productName, String productDescription, String avatar, int priceS, int priceM, int priceL) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.avatar = avatar;
        this.priceS = priceS;
        this.priceM = priceM;
        this.priceL = priceL;
    }

    public ProductDetail(int productId, String productName, String productDescription, String avatar, int priceS, int priceM, int priceL) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.avatar = avatar;
        this.priceS = priceS;
        this.priceM = priceM;
        this.priceL = priceL;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getPriceS() {
        return priceS;
    }

    public void setPriceS(int priceS) {
        this.priceS = priceS;
    }

    public int getPriceM() {
        return priceM;
    }

    public void setPriceM(int priceM) {
        this.priceM = priceM;
    }

    public int getPriceL() {
        return priceL;
    }

    public void setPriceL(int priceL) {
        this.priceL = priceL;
    }
}
