package com.example.demo.mapper;

import com.example.demo.dto.DriverDto;
import com.example.demo.dto.UpdateDriverDto;
import com.example.demo.model.Driver;
import com.example.demo.queries.DriverQueries;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
public class DriverMapper {
    public Driver driverDtoToDriver(DriverDto driverDto) {
        return new Driver(driverDto.getName(), driverDto.getEmail(), driverDto.getCity());
    }

    public Driver driverUpdateToDriver(UpdateDriverDto updateDriverDto) {
        return new Driver(updateDriverDto.getId(), updateDriverDto.getName(), updateDriverDto.getCity());
    }

}
