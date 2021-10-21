package com.switchfullygroupproject.marsdigibooky.domain.person;

import java.util.UUID;

public class Librarian extends Person {

    public Librarian(UUID uuid, String issn, String firstName, String lastName, String emailAdress) {
        super(uuid, issn, firstName, lastName, emailAdress);
    }
}
