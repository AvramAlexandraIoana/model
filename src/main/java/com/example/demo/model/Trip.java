package com.example.demo.model;

public class Trip {
    private int id;
    private String fromAddress;
    private String toAddress;
    private int driverId;


    public Trip() {
    }

    public Trip(int id, String fromAddress, String toAddress, int driverId) {
        this.id = id;
        this.fromAddress = fromAddress;
        this.toAddress = toAddress;
        this.driverId = driverId;
    }

    public Trip(String fromAddress, String toAddress, int driverId) {
        this.fromAddress = fromAddress;
        this.toAddress = toAddress;
        this.driverId = driverId;
    }

    public
    int getId() {
        return id;
    }

    public
    void setId(int id) {
        this.id = id;
    }

    public
    String getFromAddress() {
        return fromAddress;
    }

    public
    void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public
    String getToAddress() {
        return toAddress;
    }

    public
    void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public
    int getDriverId() {
        return driverId;
    }

    public
    void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    @Override
    public
    String toString() {
        return "Trip{" +
                "id=" + id +
                ", fromAddress='" + fromAddress + '\'' +
                ", toAddress='" + toAddress + '\'' +
                ", driverId=" + driverId +
                '}';
    }
}
