package com.switchfullygroupproject.marsdigibooky.repository;

import com.switchfullygroupproject.marsdigibooky.domain.person.Admin;
import com.switchfullygroupproject.marsdigibooky.domain.person.Person;
import com.switchfullygroupproject.marsdigibooky.domain.person.PersonDTO;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class PersonRepository {

    private final Map<UUID, Person> personDatabase;

    public PersonRepository() {
        this.personDatabase = new ConcurrentHashMap<>();
        UUID uuid = UUID.randomUUID();
        Person admin = new Admin(uuid,"4455","Mars","Man","marsman@heelal.com");
        personDatabase.put(uuid, admin);
    }

    public List<Person> findAll(){
        return new ArrayList<>(personDatabase.values());
    }
}
