package com.switchfullygroupproject.marsdigibooky.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class NoAuthorizationException extends RuntimeException {

    public NoAuthorizationException(String message) {
        super(message);
    }


}
