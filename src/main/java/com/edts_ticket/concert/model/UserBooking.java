package com.edts_ticket.concert.model;

import jakarta.persistence.*;

@Entity
@Table(name="user_booking")
public class UserBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "booking_id")
    private int bookingId;

    @Column(name = "ticket_amount")
    private int ticketAmount;

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public int getTicketAmount() {
        return ticketAmount;
    }
}
