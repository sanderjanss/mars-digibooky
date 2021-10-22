package com.switchfullygroupproject.marsdigibooky.controller;

import com.switchfullygroupproject.marsdigibooky.domain.person.*;
import com.switchfullygroupproject.marsdigibooky.exceptions.NoAuthorizationException;
import com.switchfullygroupproject.marsdigibooky.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/persons")
public class PersonController {

    private final PersonService personService;
    public final Logger logger =  LoggerFactory.getLogger(PersonController.class);

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping(path="/registermember", produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerMember(@RequestBody PersonDTO personDTO){
        if(personDTO.getUser() == User.MEMBER){
            personService.registerMember(personDTO);
        } else {
            throw new NoAuthorizationException("Only members can register themselves.");
        }
    }

}
