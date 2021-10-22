package com.switchfullygroupproject.marsdigibooky.repository;

import com.switchfullygroupproject.marsdigibooky.domain.person.*;
import com.switchfullygroupproject.marsdigibooky.exceptions.InvalidUserException;
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
        personDatabaseV2.put(admin.getUuid(), admin);
        personDatabaseV2.put(member.getUuid(), member);
        logger.warn("admin: " + admin.getUuid());
        logger.warn("member: " + member.getUuid());
    }

    public Person findById(String uuid){
        return personDatabaseV2.get(uuid);
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

    public void registerMember(Person member){
        boolean test = false;
        for (Map.Entry<String, Person> entry : personDatabaseV2.entrySet()) {
            if (entry.getValue().getInss().equals(member.getInss()) || entry.getValue().getEmailAdress().equals(member.getEmailAdress())) {
                test = true;
                break;
            }
        }
        if(!test){
            personDatabaseV2.put(member.getUuid(),member);
            logger.warn("Repo: " + member.getUuid());
        } else {
            throw new InvalidUserException("This user allready exists.");
        }
    }

}
