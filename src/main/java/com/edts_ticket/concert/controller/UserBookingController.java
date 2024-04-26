package com.edts_ticket.concert.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.CannotAcquireLockException;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.edts_ticket.concert.model.UserBooking;
import com.edts_ticket.concert.repository.BookingRepository;
import com.edts_ticket.concert.repository.UserBookingRepository;

import jakarta.persistence.LockModeType;
import jakarta.transaction.Transactional;

@RestController
public class UserBookingController {
    private  ReentrantLock lock = new ReentrantLock();

    @Autowired
    private UserBookingRepository userBookingRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @GetMapping("/user-bookings")
    public List<UserBooking> getAllUserBookings() {
        return userBookingRepository.findAllUserBookings();
    }

    @GetMapping("/user-bookings/{userId}")
    public List<UserBooking> getBookingByConcertId(@PathVariable Integer userId) {
        return userBookingRepository.findByUserId(userId);
    }

   @PostMapping("/user-booking/add")
    public String addConcert(@RequestBody UserBooking userBookingRequest) {
        if (userBookingRequest.getTicketAmount() <= 0) {
            return "invalid request";
        }

        var isLockAcquired = false;
        try { // booking lock mechanism
            isLockAcquired = lock.tryLock(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }

        if(isLockAcquired){
            try{
                Thread.sleep(1000*5);
                var booking = bookingRepository.findById(userBookingRequest.getBookingId());
                var userBookings = userBookingRepository.findByBookingId(userBookingRequest.getBookingId());
                var remainingTickets = booking.getTicketAmount();

                for (UserBooking userBooking : userBookings) {
                    remainingTickets -= userBooking.getTicketAmount(); 
                }

                if (remainingTickets < userBookingRequest.getTicketAmount()) {
                    return "input ticket amount exceed existing ticket amount";
                }

                userBookingRepository.save(userBookingRequest);
            } catch (Exception e) {
                return "error: " + e;
            }
            finally {
                lock.unlock();
            }
        } else {
            return "please retry";
        }

        return "SUCCESS";
    }
}
