package com.edts_ticket.concert.model;

import jakarta.persistence.*;

@Entity
@Table(name="booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "concert_id")
    private int concertId;

    @Column(name = "ticket_amount")
    private int ticketAmount;

    @Column(name = "book_start_date")
    private String bookStartDate;

    @Column(name = "book_end_date")
    private String bookEndDate;

    public int getId() {
        return id;
    }

    public int getConcertId() {
        return concertId;
    }

    public int getTicketAmount() {
        return ticketAmount;
    }

    public String getBookStartDate() {
        return bookStartDate;
    }

    public String getBookEndDate() {
        return bookEndDate;
    }

    // Setter
    // public void setName(String newName) {
    //     this.name = newName;
    // }

    // public void setPhoneNumber(String newPhoneNumber) {
    //     this.phoneNumber = newPhoneNumber;
    // }
}
