package com.switchfullygroupproject.marsdigibooky.exceptions;

public class PersonDoesnotExistException extends RuntimeException{
    public PersonDoesnotExistException(String message) {
        super(message);
    }
}
