package com.switchfullygroupproject.marsdigibooky.repository;

import com.switchfullygroupproject.marsdigibooky.domain.person.*;
import com.switchfullygroupproject.marsdigibooky.exceptions.InvalidUserException;
import com.switchfullygroupproject.marsdigibooky.exceptions.PersonDoesnotExistException;
import com.switchfullygroupproject.marsdigibooky.exceptions.NoAuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


@Repository
public class PersonRepository {

    private final Map<String, Person> personDatabaseV2;
    public final Logger logger =  LoggerFactory.getLogger(PersonRepository.class);

    public PersonRepository() {
        this.personDatabaseV2 = new ConcurrentHashMap<>();
        Person admin = new Person("4455","Mars","Man","marsman@heelal.com"
        , new Address("Meir", 1, "2000", "Antwerpen"),  User.ADMIN);

        Person member = new Person("1","Mars","Vrouw","marsvrouw@heelal.com",
                new Address("meir", 1, "2610", "Antwerpen"),  User.MEMBER);
        Person librarian = new Person("2","Mars","Mercurius","mercurius@heelal.com",
                new Address("meir", 1, "2610", "Antwerpen"),  User.LIBRARIAN);
        personDatabaseV2.put(admin.getUuid(), admin);
        personDatabaseV2.put(member.getUuid(), member);
        personDatabaseV2.put(librarian.getUuid(), librarian);
        logger.warn("admin: " + admin.getUuid());
        logger.warn("member: " + member.getUuid());
        logger.warn("librarian: " + librarian.getUuid());
    }

    public boolean contains(String uuid){
        return personDatabaseV2.containsKey(uuid);
    }

    public Person findById(String uuid){
        var foundPerson = this.personDatabaseV2.get(uuid);
        if(foundPerson == null) {
            throw new PersonDoesnotExistException(String.format("Person with id %s does not exist", uuid));
        }
        return foundPerson;
    }

    public List<Person> findAllMembers(){
        List<Person> memberList = new ArrayList<>();
        for (Map.Entry<String, Person> entry : personDatabaseV2.entrySet()) {
            if (entry.getValue().getUser() == User.MEMBER){
                memberList.add(entry.getValue());
            }
        }
        return memberList;
    }

    public void registerMember(Person person){
        if(userAllreadyPartOfDatabase(person)){
            personDatabaseV2.put(person.getUuid(),person);
        }
    }

    public void registerAdmin(Person person, String id){
        if(personDatabaseV2.get(id).getUser() == User.ADMIN){
            if(userAllreadyPartOfDatabase(person)){
                personDatabaseV2.put(person.getUuid(),person);
            }
        }
        else throw new NoAuthorizationException("Only an admin can register admins and librarians.");
    }

    private boolean userAllreadyPartOfDatabase(Person person){
        boolean test = false;
        for (Map.Entry<String, Person> entry : personDatabaseV2.entrySet()) {
            if (entry.getValue().getInss().equals(person.getInss()) || entry.getValue().getEmailAdress().equals(person.getEmailAdress())) {
                test = true;
                break;
            }
        }
        if(!test){
            return true;
        } else {
            throw new InvalidUserException("This user allready exists.");
        }
    }

}
