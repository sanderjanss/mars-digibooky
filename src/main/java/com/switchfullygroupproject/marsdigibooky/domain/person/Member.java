package com.switchfullygroupproject.marsdigibooky.domain.person;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.UUID;

public class Member extends Person {

    private final Address address;

    public Member(String issn, String firstName, String lastName, String emailAdress, Address address) {
        super(issn, firstName, lastName, emailAdress);
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }
}
