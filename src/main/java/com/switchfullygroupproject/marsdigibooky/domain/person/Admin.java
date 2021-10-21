package com.switchfullygroupproject.marsdigibooky.domain.person;

import java.util.UUID;

public class Admin extends Person{
    public Admin(UUID uuid, String issn, String firstName, String lastName, String emailAdress) {
        super(uuid, issn, firstName, lastName, emailAdress);
    }
}
