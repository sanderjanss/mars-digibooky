package com.switchfullygroupproject.marsdigibooky.controller;

import com.switchfullygroupproject.marsdigibooky.repository.RentalRepository;
import com.switchfullygroupproject.marsdigibooky.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/members")
public class MemberController {

    private final RentalService rentalService;

    @Autowired
    public MemberController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void lendBook(String memberId, String bookId){
        rentalService.lendBook(memberId, bookId);
    }
}
