package com.switchfullygroupproject.marsdigibooky.service;

import com.switchfullygroupproject.marsdigibooky.domain.person.PersonDTO;
import com.switchfullygroupproject.marsdigibooky.mapper.PersonMapper;
import com.switchfullygroupproject.marsdigibooky.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    @Autowired
    public PersonService(PersonRepository personRepository, PersonMapper personMapper) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
    }

    public List<PersonDTO> findAll(){
        return personMapper.toDto(personRepository.findAll());
    }
}
