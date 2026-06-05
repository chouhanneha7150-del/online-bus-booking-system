package com.busbooking;

import com.busbooking.bus.Bus;
import com.busbooking.buscontroller.BusController;
import com.busbooking.busrepository.BusRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BusControllerTest {

    @Mock
    private BusRepository repository;

    @Mock
    private Model model;

    @InjectMocks
    private BusController controller;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    // Positive Case
    @Test
    void saveBus_success() {

        Bus bus = new Bus();
        bus.setBusName("Volvo");

        when(repository.save(bus))
                .thenReturn(bus);

        String result = controller.saveBus(bus);

        assertEquals(
                "Bus Added Successfully",
                result);

        verify(repository,
                times(1))
                .save(bus);
    }

    // Edge Case
    @Test
    void saveBus_emptyBus() {

        Bus bus = new Bus();

        when(repository.save(bus))
                .thenReturn(bus);

        String result = controller.saveBus(bus);

        assertEquals(
                "Bus Added Successfully",
                result);
    }

    // Negative Case
    @Test
    void saveBus_exception() {

        Bus bus = new Bus();

        when(repository.save(bus))
                .thenThrow(
                        new RuntimeException("DB Error"));

        assertThrows(
                RuntimeException.class,
                () -> controller.saveBus(bus));
    }

    // View All Buses
    @Test
    void getAllBuses_found() {

        when(repository.findAll())
                .thenReturn(
                        Arrays.asList(
                                new Bus(),
                                new Bus()));

        String result =
                controller.getAllBuses(model);

        assertEquals("buses", result);

        verify(model)
                .addAttribute(
                        eq("buses"),
                        any());
    }

    // Empty Bus List
    @Test
    void getAllBuses_empty() {

        when(repository.findAll())
                .thenReturn(
                        Collections.emptyList());

        String result =
                controller.getAllBuses(model);

        assertEquals("buses", result);
    }

    // Search Bus Found
    @Test
    void searchBus_found() {

        Bus bus = new Bus();

        when(repository.findBySourceAndDestination(
                "Delhi",
                "Mumbai"))
                .thenReturn(
                        Arrays.asList(bus));

        Object result =
                controller.searchBus(
                        "Delhi",
                        "Mumbai");

        assertNotNull(result);
    }

    // Search Bus Not Found
    @Test
    void searchBus_notFound() {

        when(repository.findBySourceAndDestination(
                "Delhi",
                "Mumbai"))
                .thenReturn(
                        Collections.emptyList());

        Object result =
                controller.searchBus(
                        "Delhi",
                        "Mumbai");

        assertNotNull(result);
    }

    // Search Bus Exception
    @Test
    void searchBus_exception() {

        when(repository.findBySourceAndDestination(
                anyString(),
                anyString()))
                .thenThrow(
                        new RuntimeException("DB Error"));

        assertThrows(
                RuntimeException.class,
                () -> controller.searchBus(
                        "Delhi",
                        "Mumbai"));
    }
}