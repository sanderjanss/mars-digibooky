package com.switchfullygroupproject.marsdigibooky.domain.person;

import java.util.UUID;

public class MemberDTO extends PersonDTO{
    private final Address address;

    public MemberDTO(String issn, String firstName, String lastName, String emailAdress, Address address) {
        super(issn, firstName, lastName, emailAdress);
        this.address = address;
    }
    public MemberDTO(String firstName, String lastName, String emailAdress, Address address) {
        super( firstName, lastName, emailAdress);
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }
}
