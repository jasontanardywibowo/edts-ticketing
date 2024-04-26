package com.edts_ticket.concert.repository;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.edts_ticket.concert.model.UserBooking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface UserBookingRepository extends JpaRepository<UserBooking, Integer> {
    @Query(value="SELECT ub FROM UserBooking ub")
    List<UserBooking> findAllUserBookings();

    List<UserBooking> findByUserId(int userId);
    List<UserBooking> findByBookingId(int bookingId);
}
