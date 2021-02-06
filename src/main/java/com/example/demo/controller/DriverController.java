package com.example.demo.controller;

import com.example.demo.dto.DriverDto;
import com.example.demo.dto.UpdateDriverDto;
import com.example.demo.mapper.DriverMapper;
import com.example.demo.model.Driver;
import com.example.demo.service.DriverService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/drivers")
public class DriverController {
    private DriverService driverService;
    private DriverMapper driverMapper;

    public DriverController(DriverService driverService, DriverMapper driverMapper) {
        this.driverService = driverService;
        this.driverMapper = driverMapper;
    }

    @PostMapping("/create")
    public
    ResponseEntity<Driver> create(@RequestBody @Valid DriverDto driverDto) {
        Driver savedDriver = driverService.create(
                driverMapper.driverDtoToDriver(driverDto));
        return ResponseEntity.created(UriComponentsBuilder.
                fromHttpUrl(ServletUriComponentsBuilder.
                        fromCurrentRequestUri().
                        toUriString())
                .replacePath("drivers/" + savedDriver.getId())
                .build(savedDriver.getId()))
                .body(savedDriver);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Driver> update(@PathVariable  int id, @RequestBody @Valid UpdateDriverDto updateDriverDto) {
        if (id != updateDriverDto.getId()) {
            throw  new RuntimeException("Nu se potrivesc!");
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(driverService.update(driverMapper.driverUpdateToDriver(updateDriverDto)));
    }
}
