package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import com.example.demo.model.Driver;
import com.example.demo.repository.DriverRepository;
import com.example.demo.utils.DuplicateEmailException;
import com.example.demo.utils.ErrorResponse;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DriverServiceTest {
    //@Mock, @InjectMocks
    @InjectMocks
    private DriverService driverService;

    @Mock
    private DriverRepository driverRepository;

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
    @DisplayName("Adaugarea unui driver")
    public void createTest() {
        //arrange
        Driver driver = new Driver("Ioana", "ioana@gmail.com", "Bucuresti");
        Driver savedDriver = new Driver(1, "Ioana", "ioana@gmail.com", "Bucuresti");
        when(driverRepository.getByEmail(driver.getEmail())).thenReturn(Optional.empty());
        when(driverRepository.create(driver)).thenReturn(savedDriver);

        //act
        Driver result = driverService.create(driver);

        //assert
        assertEquals(result.getId(), 1);
        assertEquals(driver.getName(), result.getName());
        assertEquals(driver.getEmail(), result.getEmail());
        assertEquals(driver.getCity(), result.getCity());

        verify(driverRepository, times(1)).getByEmail(driver.getEmail());
        verify(driverRepository, times(1)).create(driver);
    }

    @Test
    @DisplayName("Adaugarea unui driver cu acelasi email")
    public void createWithSameEmailTest() {
        //arrange
        Driver driver = new Driver("Ioana", "ioana@gmail.com", "Bucuresti");
        Driver savedDriver = new Driver(1, "Ioana", "ioana@gmail.com", "Bucuresti");
        when(driverRepository.getByEmail(driver.getEmail())).thenReturn(Optional.of(savedDriver));


        //assert
        DuplicateEmailException exception = assertThrows(DuplicateEmailException.class, () -> driverService.create(driver));
        assertEquals("Exista acest email!!", exception.getMessage());


        verify(driverRepository, times(1)).getByEmail(driver.getEmail());
        verify(driverRepository, times(0)).create(driver);
    }

    @Test
    @DisplayName("Updatarea unei driver")
    public void updateTest() {
        //arrange
        Driver driver = new Driver(1,"Ioana", "Targu-Jiu");
        Driver updateDriver = new Driver(1,"Ioana", "ioana@gmail.com", "Targu-Jiu");

        when(driverRepository.update(driver)).thenReturn(updateDriver);

        //act
        Driver result = driverService.update(driver);

        //assert
        assertEquals(driver.getId(), updateDriver.getId());
        assertEquals(driver.getName(), result.getName());
        assertEquals(driver.getCity(), result.getCity());

        verify(driverRepository, times(1)).update(driver);
    }

}