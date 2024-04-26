package com.edts_ticket.concert.repository;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.edts_ticket.concert.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value="SELECT u FROM User u")
    List<User> findAllUsers();
}
