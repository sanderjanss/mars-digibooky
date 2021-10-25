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


    public String authorLastName(String lastName) {
        if(!(lastName == null)){
            return lastName;
        }
        throw new IllegalArgumentException("Last name cant be null.");
    }

}
