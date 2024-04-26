package com.edts_ticket.concert.controller;

import java.util.List;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.edts_ticket.concert.model.Concert;
import com.edts_ticket.concert.repository.ConcertRepository;


@RestController
public class ConcertController{
    @Autowired
    private ConcertRepository concertRepository;

    @GetMapping("/concerts")
    public List<Concert> getAllConcerts() {
        return concertRepository.findAllConcerts();
    }

    @PostMapping("/concert/add")
    public String addConcert(@RequestBody Concert concertRequest) {
        try {
            concertRepository.save(concertRequest);
        }  catch (Exception e) {
            return "something went wrong: "+ e;
        }

        return "SUCCESS";
    }
}
