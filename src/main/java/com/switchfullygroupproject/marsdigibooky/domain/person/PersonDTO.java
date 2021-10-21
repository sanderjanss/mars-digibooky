package com.switchfullygroupproject.marsdigibooky.domain.person;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.UUID;

public class PersonDTO {
    private final UUID uuid;
    private final String issn;
    private final String firstName;
    private final String lastName;
    private final String emailAdress;


    public PersonDTO(UUID uuid, String issn, String firstName, String lastName, String emailAdress) {
        this.uuid = uuid;
        this.issn = issn;
        this.firstName = firstName;
        this.lastName = lastNameNotNull(lastName);
        this.emailAdress = isValidEmailAddress(emailAdress);

    }

    public UUID getUuid() {
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
