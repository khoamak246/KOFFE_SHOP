package com.koffe.koffe.model;

import java.util.List;

public class Dashboard_Data {
    private int totalUser;
    private int totalOrder;
    private int totalReservation;
    private int totalSales;
    private List<Order_Amount_Product> orderProductList;
    private List<Integer> monthSale;
    private List<Integer> comments;

    public Dashboard_Data() {
    }

    public Dashboard_Data(int totalUser, int totalOrder, int totalReservation, int totalSales, List<Order_Amount_Product> orderProductList, List<Integer> monthSale, List<Integer> comments) {
        this.totalUser = totalUser;
        this.totalOrder = totalOrder;
        this.totalReservation = totalReservation;
        this.totalSales = totalSales;
        this.orderProductList = orderProductList;
        this.monthSale = monthSale;
        this.comments = comments;
    }

    public int getTotalUser() {
        return totalUser;
    }

    public void setTotalUser(int totalUser) {
        this.totalUser = totalUser;
    }

    public int getTotalOrder() {
        return totalOrder;
    }

    public void setTotalOrder(int totalOrder) {
        this.totalOrder = totalOrder;
    }

    public int getTotalReservation() {
        return totalReservation;
    }

    public void setTotalReservation(int totalReservation) {
        this.totalReservation = totalReservation;
    }

    public int getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(int totalSales) {
        this.totalSales = totalSales;
    }

    public List<Order_Amount_Product> getOrderProductList() {
        return orderProductList;
    }

    public void setOrderProductList(List<Order_Amount_Product> orderProductList) {
        this.orderProductList = orderProductList;
    }

    public List<Integer> getMonthSale() {
        return monthSale;
    }

    public void setMonthSale(List<Integer> monthSale) {
        this.monthSale = monthSale;
    }

    public List<Integer> getComments() {
        return comments;
    }

    public void setComments(List<Integer> comments) {
        this.comments = comments;
    }
}
