package com.busbooking.bookingcontroller;

import com.busbooking.booking.Booking;
import com.busbooking.bookingrepository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BookingController {

    @Autowired
    BookingRepository repository;

    // SAVE BOOKING
    @PostMapping("/saveBooking")
    public String saveBooking(Booking booking,
                              Model model) {

        Booking existingSeat =
                repository.findByBusNameAndSeatNumber(
                        booking.getBusName(),
                        booking.getSeatNumber()
                );

        if (existingSeat != null) {

            model.addAttribute(
                    "message",
                    "Seat Already Booked!"
            );

            return "booking";
        }

        Booking savedTicket =
                repository.save(booking);

        model.addAttribute(
                "ticket",
                savedTicket
        );

        return "ticket";
    }

    // VIEW BOOKINGS
    @GetMapping("/bookings")
    public String getAllBookings(Model model) {

        model.addAttribute(
                "bookings",
                repository.findAll()
        );

        return "bookings";
    }

    // USER HISTORY
    @GetMapping("/history")
    public String userHistory(
            @RequestParam(required = false)
            String userName,
            Model model) {

        if (userName != null) {

            model.addAttribute(
                    "bookings",
                    repository.findByUserName(userName)
            );
        }

        return "history";
    }
}