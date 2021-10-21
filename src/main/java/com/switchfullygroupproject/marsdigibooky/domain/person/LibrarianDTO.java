package com.switchfullygroupproject.marsdigibooky.domain.person;

import java.util.UUID;

public class LibrarianDTO extends PersonDTO{
    public LibrarianDTO(UUID uuid, String issn, String firstName, String lastName, String emailAdress) {
        super(uuid, issn, firstName, lastName, emailAdress);
    }
}
