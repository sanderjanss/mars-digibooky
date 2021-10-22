package com.switchfullygroupproject.marsdigibooky.domain.person;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.UUID;

public class PersonDTOV2 {

    private String uuid;
    private String inss;
    private final String firstName;
    private final String lastName;
    private final String emailAdress;
    private final User user;


    public PersonDTOV2(String inss, String firstName, String lastName, String emailAdress, User user) {
        this.uuid = UUID.randomUUID().toString();
        this.inss = inss;
        this.firstName = firstName;
        this.lastName = lastNameNotNull(lastName);
        this.emailAdress = isValidEmailAddress(emailAdress);
        this.user = user;
    }
    public PersonDTOV2(String firstName, String lastName, String emailAdress, User user) {
        this.uuid = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.lastName = lastNameNotNull(lastName);
        this.emailAdress = isValidEmailAddress(emailAdress);
        this.user = user;
    }


    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @JsonIgnore
    public String getInss() {
        return inss;
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