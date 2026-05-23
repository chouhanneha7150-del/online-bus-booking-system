package com.busbooking.bookingservice;

import com.busbooking.booking.Booking;
import com.busbooking.bookingrepository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    @Autowired
    BookingRepository repository;

    public Booking saveBooking(Booking booking) {
        return repository.save(booking);
    }
}