package com.example.demo.mapper;

import com.example.demo.dto.DriverDto;
import com.example.demo.dto.TripDto;
import com.example.demo.model.Driver;
import com.example.demo.model.Trip;
import org.springframework.stereotype.Component;

@Component
public class TripMapper {
    public Trip tripDtoToTrip(TripDto tripDto) {
        return new Trip(tripDto.getFromAddress(), tripDto.getToAddress(), tripDto.getDriverId());
    }
}
