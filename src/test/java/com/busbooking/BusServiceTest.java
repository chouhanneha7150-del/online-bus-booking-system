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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class BusServiceTest {

    @Mock
    private BusRepository busRepository;

    @InjectMocks
    private BusService busService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllBuses() {

        Bus bus1 = new Bus();
        bus1.setBusName("Volvo");

        Bus bus2 = new Bus();
        bus2.setBusName("Express");

        when(busRepository.findAll())
                .thenReturn(Arrays.asList(bus1, bus2));

        List<Bus> buses = busService.getAllBuses();

        assertEquals(2, buses.size());
    }
}