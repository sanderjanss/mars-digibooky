package com.switchfullygroupproject.marsdigibooky.controller;

import com.switchfullygroupproject.marsdigibooky.domain.person.Address;
import com.switchfullygroupproject.marsdigibooky.domain.person.MemberDTO;
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
@RequestMapping(path = "/persons")
public class PersonController {

    private final PersonService personService;
    public final Logger logger =  LoggerFactory.getLogger(PersonController.class);

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(path = "{/uuid}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public PersonDTO findById(@PathVariable String uuid){
        return personService.findById(uuid);
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<PersonDTO> findAllMembers(){
        return personService.findAllMembers();
    }

    @PostMapping( produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerMember(){
        MemberDTO member = new MemberDTO("1234", "Myrthe", "Ververs", "Myrthe@ver.com",
                new Address("Meir", 1, "2000", "Antwerp"));
         personService.registermember(member);
         logger.warn("Controller: " + member.getUuid());

    }
}
