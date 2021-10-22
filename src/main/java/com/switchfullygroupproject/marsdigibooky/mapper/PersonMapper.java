package com.switchfullygroupproject.marsdigibooky.mapper;

import com.switchfullygroupproject.marsdigibooky.domain.person.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonMapper {
    public List<PersonDTO> toDto(List<Person> personList) {
        return personList.stream().map(this::toDto).collect(Collectors.toList());
    }

    public PersonDTO toDto(Person person) {
        PersonDTO personDTO = new PersonDTO(person.getInss(), person.getFirstName(), person.getLastName(), person.getEmailAdress(), person.getAddress(), person.getUser());
        personDTO.setUuid(person.getUuid());
        return personDTO;
    }

    public Person toPerson(PersonDTO personDTO) {
        Person person = new Person(personDTO.getInss(), personDTO.getFirstName(), personDTO.getLastName(), personDTO.getEmailAdress(),
                personDTO.getAddress(), personDTO.getUser());
        person.setUuid(personDTO.getUuid());
        return person;

    }


}
