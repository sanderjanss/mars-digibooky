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
    public final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    public AdminController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(path = "/{id}/allmembers", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<PersonDTO> findAllMembers(@PathVariable String id) {
        List<PersonDTO> allMembers = personService.findAllMembers(id);
        logger.info(String.format("Retrieved all members by admin with id %s", id));
        return allMembers;
    }

    @PostMapping(path = "/{id}/registeradminorlibrarian", produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerAdminOrLibrarian(@RequestBody PersonDTO personDTO, @PathVariable String id) {
        personService.registerAdmin(personDTO, id);
        logger.info(String.format("Registered a new admin/librarian by admin with id %s", id));
    }
}
