package com.koffe.koffe.model;

public class Address {
    private int addressId;
    private String Location;
    private int userId;

    public Address() {
    }

    public Address(String location, int userId) {
        Location = location;
        this.userId = userId;
    }

    public Address(int addressId, String location) {
        this.addressId = addressId;
        Location = location;
    }

    public Address(int addressId, String location, int userId) {
        this.addressId = addressId;
        Location = location;
        this.userId = userId;
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

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }
}
