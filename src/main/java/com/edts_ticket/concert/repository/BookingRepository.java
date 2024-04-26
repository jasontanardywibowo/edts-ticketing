package com.edts_ticket.concert.repository;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.edts_ticket.concert.model.Booking;
import com.edts_ticket.concert.model.Concert;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    @Query(value="SELECT id, concert_id, ticket_amount, book_start_date, book_end_date FROM booking WHERE book_start_date < NOW() AND book_end_date > NOW();", nativeQuery = true)
    List<Booking> findAllBookings();

    List<Booking> findByConcertId(int concertId);

    Booking findById(int id);
}