package com.edts_ticket.concert.model;

import jakarta.persistence.*;

@Entity
@Table(name="concert")
public class Concert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "ticket_amount")
    private int ticketAmount;

    @Column(name = "event_date")
    private String eventDate;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getTicketAmount() {
        return ticketAmount;
    }

    public String getEventDate() {
        return eventDate;
    }
}
