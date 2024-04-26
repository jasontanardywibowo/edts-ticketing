package com.edts_ticket.concert.repository;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.edts_ticket.concert.model.Concert;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface ConcertRepository extends JpaRepository<Concert, Integer> {
    @Query(value="SELECT c FROM Concert c")
    List<Concert> findAllConcerts();

    Concert findById(int id);
}
