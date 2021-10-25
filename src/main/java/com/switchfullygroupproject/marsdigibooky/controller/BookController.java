package com.switchfullygroupproject.marsdigibooky.controller;

import com.switchfullygroupproject.marsdigibooky.domain.book.BookDetailDTO;
import com.switchfullygroupproject.marsdigibooky.exceptions.BookDoesNotExistException;
import com.switchfullygroupproject.marsdigibooky.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
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
        try {
            return this.bookService.getBookById(uuid);
        } catch (BookDoesNotExistException bdnee) {
            logger.error(bdnee.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bdnee.getMessage());
        }
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
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
