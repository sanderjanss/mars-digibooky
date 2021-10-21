package com.switchfullygroupproject.marsdigibooky.domain.person;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.UUID;

public class Person {

    private final String uuid;
    private final String issn;
    private final String firstName;
    private final String lastName;
    private final String emailAdress;


    public Person(String ISSN, String firstName, String lastName, String emailAdress) {
        this.uuid = UUID.randomUUID().toString();
        this.issn = ISSN;
        this.firstName = firstName;
        this.lastName = lastNameNotNull(lastName);
        this.emailAdress = isValidEmailAddress(emailAdress);

    }

    public String getUuid() {
        return uuid;
    }

    public String getIssn() {
        return issn;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailAdress() {
        return emailAdress;
    }

    public String lastNameNotNull(String lastName) {
        if(!(lastName == null)){
            return lastName;
        }
        throw new IllegalArgumentException("Last name cant be null.");
    }



    public String isValidEmailAddress(String email) {
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
           throw new IllegalArgumentException("Not a valid emailAdress");
        }
        return email;
    }

}
