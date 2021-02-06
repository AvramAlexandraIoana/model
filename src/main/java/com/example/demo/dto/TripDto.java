package com.example.demo.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TripDto {
    @NotBlank(message = "FromAddress nu poate fi null!")
    @Size(max = 200, message = "FromAddress dimensiune de cel mult 200!")
    private String  fromAddress;

    @NotBlank(message = "ToAdress nu poate fi null!")
    @Size(max = 200, message = "ToAdress dimensiune de cel mult 200!")
    private String toAddress;

    @NotNull(message = "DriverId nu poate fi null!")
    private int driverId;

    public TripDto() {
    }

    public TripDto(String fromAddress, String toAddress, int driverId) {
        this.fromAddress = fromAddress;
        this.toAddress = toAddress;
        this.driverId = driverId;
    }

    public String getFromAddress() {
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
}
