package com.koffe.koffe.model;

import java.sql.Timestamp;
import java.util.Date;

public class BookingTable {
    private int bookingTableId;
    private Timestamp arrivedTime;
    private int AmountPeople;
    private String phoneNumber;
    private String name;
    private boolean status;

    public BookingTable() {
    }

    public BookingTable(Timestamp arrivedTime, int amountPeople, String phoneNumber, String name) {
        this.arrivedTime = arrivedTime;
        AmountPeople = amountPeople;
        this.phoneNumber = phoneNumber;
        this.name = name;
    }

    public BookingTable(int bookingTableId, Timestamp arrivedTime, int amountPeople, String phoneNumber, String name, boolean status) {
        this.bookingTableId = bookingTableId;
        this.arrivedTime = arrivedTime;
        AmountPeople = amountPeople;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.status = status;
    }

    public int getBookingTableId() {
        return bookingTableId;
    }

    public void setBookingTableId(int bookingTableId) {
        this.bookingTableId = bookingTableId;
    }

    public Timestamp getArrivedTime() {
        return arrivedTime;
    }

    public void setArrivedTime(Timestamp arrivedTime) {
        this.arrivedTime = arrivedTime;
    }

    public int getAmountPeople() {
        return AmountPeople;
    }

    public void setAmountPeople(int amountPeople) {
        AmountPeople = amountPeople;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
