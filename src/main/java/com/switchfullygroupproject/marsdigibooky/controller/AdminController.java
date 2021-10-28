package com.switchfullygroupproject.marsdigibooky.controller;

import com.switchfullygroupproject.marsdigibooky.domain.person.PersonDTO;
import com.switchfullygroupproject.marsdigibooky.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin // CODEREVIEW who is this here? (x5)
@RequestMapping("/admins")
public class AdminController {
    private final PersonService personService;
    public final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    public AdminController(PersonService personService) {
        this.personService = personService;
    }

    // CODEREVIEW be putting the logging on the end of your method, you prevent it from happening if an exception occurs (x20)
    @GetMapping(path = "/{id}/allmembers", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    // CODEREVIEW id is a word without meaning: a more descriptive name like authenticatedUserId is preferable
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
