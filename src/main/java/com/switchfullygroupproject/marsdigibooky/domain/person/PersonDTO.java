package com.switchfullygroupproject.marsdigibooky.domain.person;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.switchfullygroupproject.marsdigibooky.exceptions.InvalidUserException;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.UUID;

public class PersonDTO {

    private String uuid;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // CODEREVIEW what is this doing here ?
    private String inss;
    private final String firstName;
    private final String lastName;
    private final String emailAdress;
    private final Address address;
    private final User user;


    public PersonDTO(String inss, String firstName, String lastName, String emailAdress, Address address, User user) {
        this.uuid = UUID.randomUUID().toString();
        this.inss = inss;
        this.firstName = firstName;
        this.lastName = lastNameNotNull(lastName);
        this.emailAdress = isValidEmailAddress(emailAdress);
        this.user = user;
        this.address = address;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }


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

    public Address getAddress() {
        return address;
    }

    public User getUser() {
        return user;
    }

    public String lastNameNotNull(String lastName) {
        if (!(lastName == null)) {
            return lastName;
        }
        throw new InvalidUserException("Last name cant be null.");
    }


    public String isValidEmailAddress(String email) {
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            throw new InvalidUserException("Not a valid emailAdress");
        }
        return email;
    }
}
