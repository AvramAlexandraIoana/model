package com.example.demo.service;

import com.example.demo.model.Driver;
import com.example.demo.model.Trip;
import com.example.demo.queries.TripQueries;
import com.example.demo.repository.DriverRepository;
import com.example.demo.repository.TripRepository;
import com.example.demo.utils.DuplicateEmailException;
import com.example.demo.utils.ObjectNotFoundException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TripService {
    private TripRepository tripRepository;
    private DriverRepository driverRepository;

    public TripService(TripRepository tripRepository, DriverRepository driverRepository) {
        this.tripRepository = tripRepository;
        this.driverRepository = driverRepository;
    }

    public Trip create(Trip trip) {
        Optional<Driver> existDriver = driverRepository.getDriverById(trip.getDriverId());
        if (existDriver.isEmpty()) {
            throw new ObjectNotFoundException("Nu exista driver cu acest id!!");
        }
        return tripRepository.create(trip);
    }

    public List<Trip> getAllTrips(int driverId) {
        return tripRepository.getAllTrips(driverId);
    }

}
