package com.busbooking;

import com.busbooking.bus.Bus;
import com.busbooking.busrepository.BusRepository;
import com.busbooking.busservice.BusService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BusServiceTest {

    @Mock
    private BusRepository busRepository;

    @InjectMocks
    private BusService busService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveBus_success() {

        Bus bus = new Bus();
        bus.setBusName("Volvo");

        when(busRepository.save(bus)).thenReturn(bus);

        Bus saved = busService.saveBus(bus);

        assertEquals("Volvo", saved.getBusName());
    }

    @Test
    void saveBus_emptyFields() {

        Bus bus = new Bus();

        when(busRepository.save(bus)).thenReturn(bus);

        Bus saved = busService.saveBus(bus);

        assertNotNull(saved);
    }

    @Test
    void saveBus_exception() {

        Bus bus = new Bus();

        when(busRepository.save(bus))
                .thenThrow(new RuntimeException());

        assertThrows(RuntimeException.class,
                () -> busService.saveBus(bus));
    }

    @Test
    void getAllBuses_success() {

        Bus bus1 = new Bus();
        bus1.setBusName("Volvo");

        Bus bus2 = new Bus();
        bus2.setBusName("Express");

        when(busRepository.findAll())
                .thenReturn(Arrays.asList(bus1, bus2));

        List<Bus> buses = busService.getAllBuses();

        assertEquals(2, buses.size());
    }

    @Test
    void getAllBuses_emptyList() {

        when(busRepository.findAll())
                .thenReturn(Collections.emptyList());

        List<Bus> buses = busService.getAllBuses();

        assertTrue(buses.isEmpty());
    }

    @Test
    void getAllBuses_exception() {

        when(busRepository.findAll())
                .thenThrow(new RuntimeException());

        assertThrows(RuntimeException.class,
                () -> busService.getAllBuses());
    }

    @Test
    void searchBus_found() {

        Bus bus = new Bus();

        when(busRepository.findBySourceAndDestination(
                "Delhi",
                "Mumbai"))
                .thenReturn(List.of(bus));

        List<Bus> result =
                busService.searchBus("Delhi", "Mumbai");

        assertEquals(1, result.size());
    }

    @Test
    void searchBus_notFound() {

        when(busRepository.findBySourceAndDestination(
                "Delhi",
                "Mumbai"))
                .thenReturn(Collections.emptyList());

        List<Bus> result =
                busService.searchBus("Delhi", "Mumbai");

        assertTrue(result.isEmpty());
    }

    @Test
    void searchBus_nullInput() {

        when(busRepository.findBySourceAndDestination(
                null,
                null))
                .thenReturn(Collections.emptyList());

        List<Bus> result =
                busService.searchBus(null, null);

        assertNotNull(result);
    }

    @Test
    void searchBus_exception() {

        when(busRepository.findBySourceAndDestination(
                anyString(),
                anyString()))
                .thenThrow(new RuntimeException());

        assertThrows(RuntimeException.class,
                () -> busService.searchBus("A", "B"));
    }
}