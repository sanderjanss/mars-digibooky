package com.switchfullygroupproject.marsdigibooky.controller;

import com.switchfullygroupproject.marsdigibooky.domain.person.PersonDTO;
import com.switchfullygroupproject.marsdigibooky.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/admins")
public class AdminController {
    private final PersonService personService;
    public final Logger logger = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    public AdminController(PersonService personService) {
        this.personService = personService;
    }

    //NOT VALIDATED YET WITH ADMINID
    @GetMapping(path = "/finduser/{userid}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public PersonDTO findById(@PathVariable String userid) {
        return personService.findById(userid);
    }

    @GetMapping(path = "/{id}/allmembers", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<PersonDTO> findAllMembers(@PathVariable String id) {
        return personService.findAllMembers(id);
    }

    @PostMapping(path = "/{id}/registeradminorlibrarian", produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerAdminOrLibrarian(@RequestBody PersonDTO personDTO, @PathVariable String id) {
        personService.registerAdmin(personDTO, id);
    }
}
