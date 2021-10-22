package com.switchfullygroupproject.marsdigibooky.mapper;

import com.switchfullygroupproject.marsdigibooky.domain.person.Member;
import com.switchfullygroupproject.marsdigibooky.domain.person.MemberDTO;
import com.switchfullygroupproject.marsdigibooky.domain.person.Person;
import com.switchfullygroupproject.marsdigibooky.domain.person.PersonDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonMapper {
    public List<PersonDTO> toDtoWithoutInss(List<Person> personList) {
        return personList.stream().map(this::toDtoWithoutInss).collect(Collectors.toList());
    }

    public PersonDTO toDto(Person person) {
        PersonDTO personDTO = new PersonDTO(person.getInss(), person.getFirstName(), person.getLastName(), person.getEmailAdress());
        personDTO.setUuid(personDTO.getUuid());
        return personDTO;
    }

    public Person toPerson(PersonDTO personDTO) {
        return new Person(personDTO.getInss(), personDTO.getFirstName(), personDTO.getLastName(), personDTO.getEmailAdress(), personDTO.getAddress(), personDTO.getUser());

    }

    public Person toMember(PersonDTO memberDTO) {
        Person member = new Person(memberDTO.getInss(), memberDTO.getFirstName(), memberDTO.getLastName(), memberDTO.getEmailAdress(),
                memberDTO.getAddress(), memberDTO.getUser());
        member.setUuid(memberDTO.getUuid());
        return member;

    }


}
