package com.switchfullygroupproject.marsdigibooky.exceptions;

public class BookDoesNotExistException extends RuntimeException{
    public BookDoesNotExistException(String message) {
        super(message);
    }
}
