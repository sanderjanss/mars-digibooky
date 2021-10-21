package com.switchfullygroupproject.marsdigibooky.controller;

public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(path = "{/uuid}", produces = "applicatio/json")
    @ResponseStatus(HttpStatus.OK)
    public PersonDTO findById(@PathVariable String uuid){
        return personService.findById(uuid);
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<PersonDTO> findAllMembers(){
        return personService.findAllMembers();
    }
}
