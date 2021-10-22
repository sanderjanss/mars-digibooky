package com.switchfullygroupproject.marsdigibooky.controller;

import com.switchfullygroupproject.marsdigibooky.domain.book.BookDTO;
import com.switchfullygroupproject.marsdigibooky.domain.book.BookDetailDTO;
import com.switchfullygroupproject.marsdigibooky.domain.book.CreateBookDTO;
import com.switchfullygroupproject.marsdigibooky.exceptions.LibrarianDoesnotExistException;
import com.switchfullygroupproject.marsdigibooky.service.BookService;
import com.switchfullygroupproject.marsdigibooky.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/librarian")
public class LibrarianController {

    private final BookService bookService;
    private final PersonService personService;
    public final Logger logger = LoggerFactory.getLogger(LibrarianController.class);

    public LibrarianController(BookService bookService, PersonService personService) {
        this.bookService = bookService;
        this.personService = personService;
    }

    @PostMapping(path = "/{uuid}/registerbook", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerBook(@RequestBody CreateBookDTO createBookDTO, @PathVariable String uuid) {


        if(personService.findById(uuid) == null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Librarian does not exist");
        }
        BookDTO bookDTO = this.bookService.registerBook(createBookDTO);
        logger.info(String.format("Book registered: %s", bookDTO.getUuid()));
    }
}
