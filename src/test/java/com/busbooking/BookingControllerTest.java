package com.busbooking;

import com.busbooking.booking.Booking;
import com.busbooking.bookingcontroller.BookingController;
import com.busbooking.bookingrepository.BookingRepository;
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

public class BookingControllerTest {

    @Mock
    private BookingRepository repository;

    @Mock
    private Model model;

    @InjectMocks
    private BookingController controller;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    // Positive Case - Booking Success
    @Test
    void saveBooking_success() {

        Booking booking = new Booking();
        booking.setBusName("Volvo");
        booking.setSeatNumber(1);

        when(repository.findByBusNameAndSeatNumber(
                "Volvo", 1))
                .thenReturn(null);

        when(repository.save(booking))
                .thenReturn(booking);

        String result =
                controller.saveBooking(
                        booking,
                        model);

        assertEquals("ticket", result);

        verify(repository,
                times(1))
                .save(booking);
    }

    // Negative Case - Seat Already Booked
    @Test
    void saveBooking_seatAlreadyBooked() {

        Booking booking = new Booking();
        booking.setBusName("Volvo");
        booking.setSeatNumber(1);

        when(repository.findByBusNameAndSeatNumber(
                "Volvo", 1))
                .thenReturn(new Booking());

        String result =
                controller.saveBooking(
                        booking,
                        model);

        assertEquals("booking", result);

        verify(model)
                .addAttribute(
                        "message",
                        "Seat Already Booked!");
    }

    // View All Bookings
    @Test
    void getAllBookings_found() {

        when(repository.findAll())
                .thenReturn(
                        Arrays.asList(
                                new Booking(),
                                new Booking()));

        String result =
                controller.getAllBookings(model);

        assertEquals("bookings", result);

        verify(model)
                .addAttribute(
                        eq("bookings"),
                        any());
    }

    // Edge Case - Empty Booking List
    @Test
    void getAllBookings_empty() {

        when(repository.findAll())
                .thenReturn(
                        Collections.emptyList());

        String result =
                controller.getAllBookings(model);

        assertEquals("bookings", result);
    }

    // User History Found
    @Test
    void userHistory_found() {

        when(repository.findByUserName(
                "Ashish"))
                .thenReturn(
                        Arrays.asList(
                                new Booking()));

        String result =
                controller.userHistory(
                        "Ashish",
                        model);

        assertEquals("history", result);

        verify(model)
                .addAttribute(
                        eq("bookings"),
                        any());
    }

    // Edge Case - Null Username
    @Test
    void userHistory_nullUser() {

        String result =
                controller.userHistory(
                        null,
                        model);

        assertEquals("history", result);

        verify(repository,
                never())
                .findByUserName(anyString());
    }
}