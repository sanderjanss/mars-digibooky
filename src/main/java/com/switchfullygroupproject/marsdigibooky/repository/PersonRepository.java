package com.switchfullygroupproject.marsdigibooky.repository;

import com.switchfullygroupproject.marsdigibooky.domain.person.Address;
import com.switchfullygroupproject.marsdigibooky.domain.person.Person;
import com.switchfullygroupproject.marsdigibooky.domain.person.User;
import com.switchfullygroupproject.marsdigibooky.exceptions.InvalidUserException;
import com.switchfullygroupproject.marsdigibooky.exceptions.NoAuthorizationException;
import com.switchfullygroupproject.marsdigibooky.exceptions.PersonDoesnotExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Repository
public class PersonRepository {

    private final Map<String, Person> personDatabaseV2;
    public final Logger logger = LoggerFactory.getLogger(PersonRepository.class);

    public PersonRepository() {
        this.personDatabaseV2 = new ConcurrentHashMap<>();
        Person admin = new Person("4455", "Mars", "Man", "marsman@heelal.com"
                , new Address("Meir", 1, "2000", "Antwerpen"), User.ADMIN);
        admin.setUuid("c58eb341-ee3c-4036-80af-18f7b5988ab3");
        Person member = new Person("1", "Mars", "Vrouw", "marsvrouw@heelal.com",
                new Address("meir", 1, "2610", "Antwerpen"), User.MEMBER);
        member.setUuid("8d3f0fff-3426-4156-a8bf-8d38e430813f");
        Person librarian = new Person("2", "Mars", "Mercurius", "mercurius@heelal.com",
                new Address("meir", 1, "2610", "Antwerpen"), User.LIBRARIAN);
        librarian.setUuid("a24921bb-a426-4b0d-8c6a-da8cdc62323f");
        personDatabaseV2.put(admin.getUuid(), admin);
        personDatabaseV2.put(member.getUuid(), member);
        personDatabaseV2.put(librarian.getUuid(), librarian);
        logger.warn("admin: " + admin.getUuid());
        logger.warn("member: " + member.getUuid());
        logger.warn("librarian: " + librarian.getUuid());
    }

    public boolean contains(String uuid) {
        return personDatabaseV2.containsKey(uuid);
    }

    public Person findById(String uuid) {
        var foundPerson = this.personDatabaseV2.get(uuid);
        if (foundPerson == null) {
            throw new PersonDoesnotExistException(String.format("Person with id %s does not exist", uuid));
        }
        return foundPerson;
    }

    public List<Person> findAllMembers() {
        List<Person> memberList = new ArrayList<>();
        for (Map.Entry<String, Person> entry : personDatabaseV2.entrySet()) {
            if (entry.getValue().getUser() == User.MEMBER) {
                memberList.add(entry.getValue());
            }
        }
        return memberList;
    }

    public void registerMember(Person person) {
        if (userAllreadyPartOfDatabase(person)) {
            personDatabaseV2.put(person.getUuid(), person);
        }
    }

    public void registerAdmin(Person person, String id) {
            if (userAllreadyPartOfDatabase(person)) {
                personDatabaseV2.put(person.getUuid(), person);
            }
    }

    private boolean userAllreadyPartOfDatabase(Person person) {
        boolean test = false;
        for (Map.Entry<String, Person> entry : personDatabaseV2.entrySet()) {
            if (entry.getValue().getInss().equals(person.getInss()) || entry.getValue().getEmailAdress().equals(person.getEmailAdress())) {
                test = true;
                break;
            }
        }
        if (!test) {
            return true;
        } else {
            throw new InvalidUserException("This user allready exists.");
        }
    }

}
