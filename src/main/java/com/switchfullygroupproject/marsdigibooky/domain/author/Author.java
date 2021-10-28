package com.switchfullygroupproject.marsdigibooky.domain.author;

public class Author {
    private final String firstName;
    private final String lastName;

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = authorLastName(lastName);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    // CODEREVIEW a weird method name. validateAuthorLastName or assertAuthorLastNameIsPresent or something similar is more communicative
    public String authorLastName(String lastName) {
        // CODEREVIEW it's a bit confusing that you "turn this method around"
        // look at how you implement Book.titleNotNull
        if (!(lastName == null)) {
            return lastName;
        }
        throw new IllegalArgumentException("Last name cant be null.");
    }

}
