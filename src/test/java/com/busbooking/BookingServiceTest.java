package com.busbooking;

import com.busbooking.booking.Booking;
import com.busbooking.bookingrepository.BookingRepository;
import com.busbooking.bookingservice.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookingServiceTest {

    @Mock
    private BookingRepository repository;

    @InjectMocks
    private BookingService bookingService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    // Positive Test
    @Test
    void saveBooking_success() {

        Booking booking = new Booking();
        booking.setUserName("Ashish");
        booking.setBusName("Volvo");

        when(repository.save(booking))
                .thenReturn(booking);

        Booking result =
                bookingService.saveBooking(booking);

        assertNotNull(result);
        assertEquals("Ashish",
                result.getUserName());

        verify(repository,
                times(1))
                .save(booking);
    }

    // Edge Case
    @Test
    void saveBooking_emptyFields() {

        Booking booking = new Booking();

        when(repository.save(booking))
                .thenReturn(booking);

        Booking result =
                bookingService.saveBooking(booking);

        assertNotNull(result);

        verify(repository,
                times(1))
                .save(booking);
    }

    // Negative Case
    @Test
    void saveBooking_exception() {

        Booking booking = new Booking();

        when(repository.save(booking))
                .thenThrow(
                        new RuntimeException(
                                "Database Error"));

        RuntimeException exception =
                assertThrows(
                        RuntimeException.class,
                        () -> bookingService.saveBooking(
                                booking));

        assertEquals(
                "Database Error",
                exception.getMessage());
    }
}