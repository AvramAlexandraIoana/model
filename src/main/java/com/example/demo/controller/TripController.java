package com.example.demo.controller;

import com.example.demo.dto.DriverDto;
import com.example.demo.dto.TripDto;
import com.example.demo.mapper.DriverMapper;
import com.example.demo.mapper.TripMapper;
import com.example.demo.model.Driver;
import com.example.demo.model.Trip;
import com.example.demo.service.DriverService;
import com.example.demo.service.TripService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/trips")
public class TripController {
    private TripService tripService;
    private TripMapper tripMapper;

    public TripController(TripService tripService, TripMapper tripMapper) {
        this.tripService = tripService;
        this.tripMapper = tripMapper;
    }

    @PostMapping("/create")
    public
    ResponseEntity<Trip> create(@RequestBody @Valid TripDto tripDto) {
        Trip savedTrip = tripService.create(
                tripMapper.tripDtoToTrip(tripDto));
        return ResponseEntity.created(UriComponentsBuilder.
                fromHttpUrl(ServletUriComponentsBuilder.
                        fromCurrentRequestUri().
                        toUriString())
                .replacePath("trips/" + savedTrip.getId())
                .build(savedTrip.getId()))
                .body(savedTrip);
    }

    @GetMapping("/getAllTrips")
    public ResponseEntity<List<Trip>> getAllTrips(@RequestParam  int driverId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(tripService.getAllTrips(driverId));
    }


}
