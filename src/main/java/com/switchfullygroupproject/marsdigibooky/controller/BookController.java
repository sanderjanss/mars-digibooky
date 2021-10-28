package com.switchfullygroupproject.marsdigibooky.controller;

import com.switchfullygroupproject.marsdigibooky.domain.book.BookDetailDTO;
import com.switchfullygroupproject.marsdigibooky.exceptions.BookDoesNotExistException;
import com.switchfullygroupproject.marsdigibooky.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@ComponentScan
@RequestMapping(path = "/books")
public class BookController {

    private final BookService bookService;
    private final Logger logger;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
        this.logger = LoggerFactory.getLogger(BookController.class);
    }

    @GetMapping(path = "/{uuid}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public BookDetailDTO getBookById(@PathVariable String uuid) {
        // CODEREVIEW you're being inconsistent about your exception handling.
        // Some controller methods catch the possible runtimeexceptions (you do this here)
        // Some controller methods catch runtimeexceptions that cannot occur (you do this in the next method)
        // Some controller methods don't catch possible runtimeexceptions at all (like in AdminController)
        // Some custom exceptions have @ResponseStatus(HttpStatus.FORBIDDEN) annotations
        // Decide on one method and use it consistently
        try {
            return this.bookService.getBookById(uuid);
        } catch (BookDoesNotExistException bdnee) {
            logger.error(bdnee.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bdnee.getMessage());
        }
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    // CODEREVIEW a good flexible design of your search functionality!
    // adding "OrNull" in variable names is not done very often
    public List<BookDetailDTO> getBooks(@RequestParam(name = "isbn", required = false) String isbnOrNull,
                                        @RequestParam(name = "title", required = false) String titleOrNull,
                                        @RequestParam(name = "authorFirstName", required = false) String firstNameOrNull,
                                        @RequestParam(name = "authorLastName", required = false) String lastNameOrNull) {

        try {
            return this.bookService.getAllBooks(isbnOrNull, titleOrNull, firstNameOrNull, lastNameOrNull);
        } catch (BookDoesNotExistException bdnee) {
            logger.error(bdnee.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bdnee.getMessage());
        }
    }


}
