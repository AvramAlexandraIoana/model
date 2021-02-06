package com.example.demo.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UpdateDriverDto {

    @NotNull(message = "Nu poate fi null id-ul!!")
    private int id;

    @NotBlank(message = "Nu poate fi null nunele!!")
    @Size(max = 100, message = "Lungime maxima de 100!")
    private String name;

    @NotBlank(message = "Nu poate fi null orasul!!")
    @Size(max = 100, message = "Lungime maxima de 100!")
    private String city;

    public UpdateDriverDto() {
    }

    public UpdateDriverDto(int id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
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
    String getName() {
        return name;
    }

    public
    void setName(String name) {
        this.name = name;
    }

    public
    String getCity() {
        return city;
    }

    public
    void setCity(String city) {
        this.city = city;
    }
}
