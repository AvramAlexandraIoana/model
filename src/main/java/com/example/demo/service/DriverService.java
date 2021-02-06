package com.example.demo.service;

import com.example.demo.model.Driver;
import com.example.demo.queries.DriverQueries;
import com.example.demo.repository.DriverRepository;
import com.example.demo.utils.DuplicateEmailException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverService {

    private DriverRepository driverRepository;

    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    public Driver create(Driver driver) {
        Optional<Driver> existingDriverWithEmail = driverRepository.getByEmail(driver.getEmail());
        if (existingDriverWithEmail.isPresent()) {
            throw  new DuplicateEmailException("Exista acest email!!");
        }
        return driverRepository.create(driver);
    }

    public Driver update(Driver driver) {
        return driverRepository.update(driver);
    }
}
