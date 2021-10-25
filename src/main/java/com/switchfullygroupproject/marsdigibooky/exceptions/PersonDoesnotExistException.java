package com.switchfullygroupproject.marsdigibooky.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PersonDoesnotExistException extends RuntimeException {
    public PersonDoesnotExistException(String message) {
        super(message);
    }
}
