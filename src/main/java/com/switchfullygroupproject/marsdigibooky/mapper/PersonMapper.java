package com.switchfullygroupproject.marsdigibooky.mapper;

import com.switchfullygroupproject.marsdigibooky.domain.person.Person;
import com.switchfullygroupproject.marsdigibooky.domain.person.PersonDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonMapper {
    public List<PersonDTO> toDto(List<Person> professorList) {
        return professorList.stream().map(this::toDto).collect(Collectors.toList());
    }
    public PersonDTO toDto(Person person) {
        return new PersonDTO(person.getUuid(),person.getIssn(), person.getFirstName(), person.getLastName(),person.getEmailAdress());
    }

    public Person toPerson(PersonDTO personDTO) {
        return new Person(personDTO.getUuid(), personDTO.getIssn(), personDTO.getFirstName(), personDTO.getLastName(), personDTO.getEmailAdress());

    }


}
