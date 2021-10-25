package com.switchfullygroupproject.marsdigibooky.repository;

import com.switchfullygroupproject.marsdigibooky.domain.person.Address;
import com.switchfullygroupproject.marsdigibooky.domain.person.Person;
import com.switchfullygroupproject.marsdigibooky.domain.person.User;
import com.switchfullygroupproject.marsdigibooky.exceptions.InvalidUserException;
import com.switchfullygroupproject.marsdigibooky.exceptions.PersonDoesnotExistException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
class PersonRepositoryTest {

    //HARDCODED ALSO USING THE DATA FROM THE PERSONREPOSITORY CONSTRUCTOR ........
    private PersonRepository personRepository;

    private Person admin;
    private Person member1;
    private Person member2;
    private Person librarian;
    private Address address1;
    private Address address2;
    private Address address3;
    private Address address4;

    @BeforeEach
    public void before() {
        personRepository = new PersonRepository();
        address1 = new Address("StellarStreet", 1, "1000", "Mars");
        address2 = new Address("StellarStreet", 2, "1000", "Mars");
        address3 = new Address("StellarStreet", 3, "1000", "Mars");
        address4 = new Address("StellarStreet", 4, "1000", "Mars");
        admin = new Person("1", "Mars", "Man", "mars@man.com", address1, User.ADMIN);
        librarian = new Person("2", "Venus", "Man", "venus@man.com", address2, User.LIBRARIAN);
        member1 = new Person("3", "Earth", "Man", "earth@man.com", address3, User.MEMBER);
        member1.setUuid("member1");
        member2 = new Person("4", "Mercurius", "Man", "mercurius@man.com", address4, User.MEMBER);
        member2.setUuid("member2");
        personRepository.registerMember(member1);
    }

    @Test
    public void givenTestDatabase_whenAskingCertainId_thenReturnTrueIfItContainsTheId() {
        Assertions.assertThat(personRepository.contains("member1")).isTrue();
    }

    @Test
    public void givenTestDatabase_whenAskingCertainId_thenReturnFalseIfItDoesntContainTheId() {
        Assertions.assertThat(personRepository.contains("member2")).isFalse();
    }

    @Test
    public void givenTestDatabase_whenFindByUnExistingId_thenThrowPersonDoesNotExistException() {
        Assertions.assertThatThrownBy(() -> personRepository.findById("-1")).isInstanceOf(PersonDoesnotExistException.class);
    }

    @Test
    public void givenTestDatabase_whenFindByExistingId_thenReturnPerson() {
        Assertions.assertThat(personRepository.findById(member1.getUuid()).getFirstName()).isEqualTo("Earth");
    }

    @Test
    public void givenTestDatabase_whenAskingForAllMembers_thenReturnAllMembers() {
        Assertions.assertThat(personRepository.findAllMembers()).contains(member1);
    }

    @Test
    public void givenTestDatabase_whenRegisteringUniqueMembers_thenAddNewMember() {
        //when
        personRepository.registerMember(member2);
        //then
        Assertions.assertThat(personRepository.contains("member2")).isTrue();
    }

    @Test
    public void givenTestDatabase_whenRegisteringExistingMembers_thenThrowInvalidUserException() {
        Assertions.assertThatThrownBy(() -> personRepository.registerMember(member1)).isInstanceOf(InvalidUserException.class);
    }

    //CONTAINS DATA FROM THE CONSTRUCTOR
//    @Test
//    public void givenTestDatabase_whenAskingForAllMembers_thenReturnAllMembers2(){
//        Assertions.assertThat(personRepository.findAllMembers()).isEqualTo(new ArrayList<>(List.of(member1)));
//
//    }


}