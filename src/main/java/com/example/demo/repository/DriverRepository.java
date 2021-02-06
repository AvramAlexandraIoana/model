package com.example.demo.repository;

import com.example.demo.model.Driver;
import com.example.demo.queries.DriverQueries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class DriverRepository {
    private JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(DriverRepository.class);

    public DriverRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Driver create(Driver driver) {
        PreparedStatementCreator preparedStatementCreator = (connection) -> {
            PreparedStatement preparedStatement = connection.prepareStatement(DriverQueries.CREATE_SQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, null);
            preparedStatement.setString(2, driver.getName());
            preparedStatement.setString(3, driver.getEmail());
            preparedStatement.setString(4, driver.getCity());
            return preparedStatement;
        };
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(preparedStatementCreator, generatedKeyHolder);
        driver.setId(generatedKeyHolder.getKey().intValue());
        logger.info("S-a adaugat soferul {}", driver);
        return driver;
    }

    public Optional<Driver> getByEmail(String email) {
        Optional<Driver> driver = getDriverFromResultSet(jdbcTemplate.query(DriverQueries.GETBYEMAIL_SQL, new BeanPropertyRowMapper<>(Driver.class), email));
        return driver;

    }

    private Optional<Driver> getDriverFromResultSet(List<Driver> drivers) {
        if (drivers != null && !drivers.isEmpty()) {
            return Optional.of(drivers.get(0));
        } else {
            return Optional.empty();
        }
    }

    public Driver update(Driver driver) {
        jdbcTemplate.update(DriverQueries.UPDATE_SQL, driver.getName(), driver.getCity(), driver.getId());
        List<Driver> updatedDriver = jdbcTemplate.query(DriverQueries.GETBYID_SQL, new BeanPropertyRowMapper<>(Driver.class), driver.getId());
        return updatedDriver.get(0);
    }

    public Optional<Driver> getDriverById(int id) {
        return  getDriverFromResultSet(jdbcTemplate.query(DriverQueries.GETBYID_SQL, new BeanPropertyRowMapper<>(Driver.class), id));
    }
}
