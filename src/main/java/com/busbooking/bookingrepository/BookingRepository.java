package com.busbooking.bookingrepository;

import com.busbooking.booking.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository
        extends JpaRepository<Booking, Integer> {

    Booking findByBusNameAndSeatNumber(
            String busName,
            int seatNumber
    );

    List<Booking> findByUserName(
            String userName
    );
}