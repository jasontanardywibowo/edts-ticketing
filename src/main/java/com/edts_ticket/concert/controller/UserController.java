package com.edts_ticket.concert.controller;

import java.util.List;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.edts_ticket.concert.model.Concert;
import com.edts_ticket.concert.model.User;
import com.edts_ticket.concert.repository.UserRepository;


@RestController
public class UserController{
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAllUsers();
    }

    @PostMapping("/user/add")
    public String addConcert(@RequestBody User userRequest) {
        try {
            userRepository.save(userRequest);
        }  catch (Exception e) {
            return "something went wrong: "+ e;
        }

        return "SUCCESS";
    }
}
