package com.edts_ticket.concert.controller;

import java.sql.Timestamp;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.edts_ticket.concert.model.Booking;
import com.edts_ticket.concert.repository.BookingRepository;
import com.edts_ticket.concert.repository.ConcertRepository;


@RestController
public class BookingController{
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ConcertRepository concertRepository;

    @GetMapping("/bookings")
    public List<Booking> getAllBookings() {
        return bookingRepository.findAllBookings();
    }

    @GetMapping("/bookings/{concertId}")
    public List<Booking> getBookingByConcertId(@PathVariable Integer concertId) {
        return bookingRepository.findByConcertId(concertId);
    }

    @PostMapping("/booking/add")
    public String addBooking(@RequestBody Booking bookingRequest) {
        var startDate = Timestamp.valueOf(bookingRequest.getBookStartDate());
        var endDate = Timestamp.valueOf(bookingRequest.getBookEndDate());
        if (bookingRequest.getTicketAmount() <= 0 || startDate.after(endDate)) {
            return "invalid request";
        }

        try {
            var concert = concertRepository.findById(bookingRequest.getConcertId());
            var bookings = bookingRepository.findByConcertId(bookingRequest.getConcertId());
            var remainingTickets = concert.getTicketAmount();

            for (Booking booking : bookings) {
                remainingTickets -= booking.getTicketAmount(); 
            }

            if (remainingTickets < bookingRequest.getTicketAmount()) {
                return "input ticket amount exceed existing ticket amount";
            }

            bookingRepository.save(bookingRequest);
        }  catch (Exception e) {
            return "something went wrong: "+ e;
        } 

        return "SUCCESS";
    }
}
