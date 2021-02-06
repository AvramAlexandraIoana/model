package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import com.example.demo.model.Driver;
import com.example.demo.model.Trip;
import com.example.demo.repository.DriverRepository;
import com.example.demo.repository.TripRepository;
import com.example.demo.utils.DuplicateEmailException;
import com.example.demo.utils.ErrorResponse;
import com.example.demo.utils.ObjectNotFoundException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TripServiceTest {

    @InjectMocks
    private DriverService driverService;

    @Mock
    private DriverRepository driverRepository;

    //@Mock, @InjectMocks
    @InjectMocks
    private TripService tripService;

    @Mock
    private TripRepository tripRepository;

    //@BeforeEach
    //@BeforeAll
    //@AfterEach
    //@AfterAll

    @BeforeEach
    public void beforeEach(){
        System.out.println("Hello from before each!!!");
    }

    @BeforeAll
    public static void beforeAll(){
        System.out.println("Hello from before all");
    }

    @AfterEach
    public  void afterEach() {
        System.out.println("Hello from after each");
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("Hello from after all");
    }

    @Test
    @DisplayName("Adaugarea unei excursii")
    public void createTest() {
        //arrange
        Driver driver = new Driver(1, "Ioana", "ioana@gmail.com", "Bucuresti");
        Trip trip = new Trip("Str.Mioritei, bl.1", "Str.Pacii, bl.2", 1);
        Trip savedTrip = new Trip(1, "Str.Mioritei, bl.1", "Str.Pacii, bl.2", 1);
        when(driverRepository.getDriverById(trip.getDriverId())).thenReturn(Optional.of(driver));
        when(tripRepository.create(trip)).thenReturn(savedTrip);

        //act
        Trip result = tripService.create(trip);

        //assert
        assertEquals(result.getId(), 1);
        assertEquals(trip.getFromAddress(), result.getFromAddress());
        assertEquals(trip.getToAddress(), result.getToAddress());
        assertEquals(trip.getDriverId(), result.getDriverId());

        verify(driverRepository, times(1)).getDriverById(trip.getDriverId());
        verify(tripRepository, times(1)).create(trip);
    }

    @Test
    @DisplayName("Adaugarea unei excursii pentru un driver inexistent")
    public void createWithNoDriverIdTest() {

        //arrange
        Trip trip = new Trip("Str.Mioritei, bl.1", "Str.Pacii, bl.2", 1);
        Trip savedTrip = new Trip(1, "Str.Mioritei, bl.1", "Str.Pacii, bl.2", 1);
        when(driverRepository.getDriverById(trip.getDriverId())).thenReturn(Optional.empty());


        //assert
        ObjectNotFoundException exception = assertThrows(ObjectNotFoundException.class, () -> tripService.create(trip));
        assertEquals("Nu exista driver cu acest id!!", exception.getMessage());


        verify(driverRepository, times(1)).getDriverById(trip.getDriverId());
        verify(tripRepository, times(0)).create(trip);
    }



}