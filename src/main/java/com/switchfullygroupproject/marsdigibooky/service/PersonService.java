package com.switchfullygroupproject.marsdigibooky.service;

import com.switchfullygroupproject.marsdigibooky.domain.person.PersonDTO;
import com.switchfullygroupproject.marsdigibooky.domain.person.User;
import com.switchfullygroupproject.marsdigibooky.exceptions.NoAuthorizationException;
import com.switchfullygroupproject.marsdigibooky.exceptions.PersonDoesnotExistException;
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

    public PersonDTO findById(String uuid){
        return personMapper.toDto(personRepository.findById(uuid));
    }

    public List<PersonDTO> findAllMembers(String id){
        if(personRepository.findById(id) == null){
            throw new PersonDoesnotExistException("This person id does not exist.");
        }

        if (personRepository.findById(id).getUser() != User.ADMIN) {
            throw new NoAuthorizationException("Only an Admin can access all members.");
        }

        return personMapper.toDto(personRepository.findAllMembers());
    }


    public void registerMember(PersonDTO personDTO){
        if(personDTO.getUser() != User.MEMBER){
            throw new NoAuthorizationException("You can only register yourself as a member.");
        }
        personRepository.registerMember(personMapper.toPerson(personDTO));
    }

    public void registerAdmin(PersonDTO personDTO, String id){
        if(personRepository.findById(id) == null){
            throw new PersonDoesnotExistException("This person id does not exist.");
        }
        if (personRepository.findById(id).getUser() != User.ADMIN) {
            throw new NoAuthorizationException("Only an admin can register admins and librarians.");
        }
        if(personDTO.getUser() == User.MEMBER){
            throw new NoAuthorizationException("Only admins and librarians can be created.");
        }
        personRepository.registerAdmin(personMapper.toPerson(personDTO), id);
    }
}
