package com.example.demo.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class DriverDto {

    @NotBlank(message = "Numele sa nu fie null!!")
    @Size(max = 100, message = "Numele trebuie sa aiba cel mult 100 caractere!!")
    private String name;

    @NotBlank(message = "Email sa nu fie null!!")
    private String email;

    @NotBlank(message = "City sa nu fie null!!")
    @Size(max = 100, message = "Numele trebuie sa aiba cel mult 100 caractere!!")
    private String city;

    public DriverDto() {
    }

    public DriverDto(String name, String email, String city) {
        this.name = name;
        this.email = email;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
