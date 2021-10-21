package com.switchfullygroupproject.marsdigibooky.domain.person;

import java.util.UUID;

public class MemberDTO extends PersonDTO{
    private final Address address;

    public MemberDTO(UUID uuid, String issn, String firstName, String lastName, String emailAdress, Address address) {
        super(uuid, issn, firstName, lastName, emailAdress);
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }
}
