package com.switchfullygroupproject.marsdigibooky.repository;

import com.switchfullygroupproject.marsdigibooky.domain.person.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


@Repository
public class PersonRepository {

    private final Map<String, Person> personDatabase;
    public final Logger logger =  LoggerFactory.getLogger(PersonRepository.class);

    public PersonRepository() {
        this.personDatabase = new ConcurrentHashMap<>();

        Person admin = new Admin("4455","Mars","Man","marsman@heelal.com");

        Person member = new Member("1","Mars","Vrouw","marsvrouw@heelal.com",
                new Address("meir", 1, "2610", "Antwerpen"));
        personDatabase.put(admin.getUuid(), admin);
        personDatabase.put(member.getUuid(), member);
        logger.warn(String.valueOf("admin: " + admin.getUuid()));
        logger.warn(String.valueOf("member: " + member.getUuid()));
    }

    public Person findById(String uuid){
        return personDatabase.get(uuid);
    }

    public List<Person> findAllMembers(){
        List<Person> memberList = new ArrayList<>();
        for (Map.Entry<String, Person> entry : personDatabase.entrySet()) {
            if (entry.getValue() instanceof Member){
                memberList.add(entry.getValue());
            }
        }
        return memberList;
    }

    public void registerMember(Member member){
          personDatabase.put(member.getUuid(),member);
          logger.warn("Repo: " + member.getUuid());
    }
}
