package com.switchfullygroupproject.marsdigibooky.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class RentalException extends RuntimeException {

    public RentalException(String message) {
        super(message);
    }
}
