package com.switchfullygroupproject.marsdigibooky.controller;

import com.switchfullygroupproject.marsdigibooky.domain.book.BookDTO;
import com.switchfullygroupproject.marsdigibooky.domain.book.CreateBookDTO;
import com.switchfullygroupproject.marsdigibooky.exceptions.BookDoesNotExistException;
import com.switchfullygroupproject.marsdigibooky.exceptions.PersonDoesnotExistException;
import com.switchfullygroupproject.marsdigibooky.service.BookService;
import com.switchfullygroupproject.marsdigibooky.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
        try {
            personService.findById(uuid);
            BookDTO bookDTO = this.bookService.registerBook(createBookDTO);
            logger.info(String.format("Book registered: %s", bookDTO.getUuid()));
        } catch (PersonDoesnotExistException exception) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, exception.getMessage());
        }
    }

    @DeleteMapping(path = "/{uuidLibrarian}/deletebook/{uuidBook}")
    @ResponseStatus(HttpStatus.GONE)
    public void deleteBook(@PathVariable("uuidLibrarian") String uuidLibrarian, @PathVariable("uuidBook") String uuidBook) {
        try{
            personService.findById(uuidLibrarian);
            this.bookService.deleteBook(uuidBook);
            logger.info(String.format("Book %s deleted", uuidBook));
        } catch (PersonDoesnotExistException | BookDoesNotExistException exception) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, exception.getMessage());
        }
    }

    @PutMapping(path = "/{uuidLibrarian}/undeletebook/{uuidBook}")
    @ResponseStatus(HttpStatus.OK)
    public void undeleteBook(@PathVariable("uuidLibrarian") String uuidLibrarian, @PathVariable("uuidBook") String uuidBook) {
        try {
            personService.findById(uuidLibrarian);
            this.bookService.unDeleteBook(uuidBook);
            logger.info(String.format("Book %s undeleted", uuidBook));
        } catch (PersonDoesnotExistException | BookDoesNotExistException exception) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, exception.getMessage());
        }
    }


}
