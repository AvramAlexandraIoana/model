package com.example.demo.repository;

import com.example.demo.model.Driver;
import com.example.demo.model.Trip;
import com.example.demo.queries.DriverQueries;
import com.example.demo.queries.TripQueries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Service
public class TripRepository {
    private JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(DriverRepository.class);

    public TripRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Trip create(Trip trip) {
        PreparedStatementCreator preparedStatementCreator = (connection) -> {
            PreparedStatement preparedStatement = connection.prepareStatement(TripQueries.CREATE_SQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, null);
            preparedStatement.setString(2, trip.getFromAddress());
            preparedStatement.setString(3, trip.getToAddress());
            preparedStatement.setInt(4, trip.getDriverId());
            return preparedStatement;
        };
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(preparedStatementCreator, generatedKeyHolder);
        trip.setId(generatedKeyHolder.getKey().intValue());
        return trip;
    }

    public List<Trip> getAllTrips(int driverId) {
        List<Trip> trips = jdbcTemplate.query(TripQueries.GETALLTRIPSFORDRIVER_SQL, new BeanPropertyRowMapper<>(Trip.class), driverId);
        return trips;
    }
}
