package com.switchfullygroupproject.marsdigibooky.controller;

import com.switchfullygroupproject.marsdigibooky.domain.book.BookDTO;
import com.switchfullygroupproject.marsdigibooky.domain.book.CreateBookDTO;
import com.switchfullygroupproject.marsdigibooky.domain.book.UpdateBookDTO;
import com.switchfullygroupproject.marsdigibooky.exceptions.BookDoesNotExistException;
import com.switchfullygroupproject.marsdigibooky.exceptions.PersonDoesnotExistException;
import com.switchfullygroupproject.marsdigibooky.service.BookService;
import com.switchfullygroupproject.marsdigibooky.service.PersonService;
import com.switchfullygroupproject.marsdigibooky.service.RentalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/librarian")
public class LibrarianController {

    private final BookService bookService;
    private final PersonService personService;
    private final RentalService rentalService;
    public final Logger logger = LoggerFactory.getLogger(LibrarianController.class);

    public LibrarianController(BookService bookService, PersonService personService, RentalService rentalService) {
        this.bookService = bookService;
        this.personService = personService;
        this.rentalService = rentalService;
    }

    @PostMapping(path = "/{uuid}/registerbook", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerBook(@RequestBody CreateBookDTO createBookDTO, @PathVariable String uuid) {
        try {
            personService.findById(uuid); // CODEREVIEW this method call does nothing
            BookDTO bookDTO = this.bookService.registerBook(uuid, createBookDTO);
            logger.info(String.format("Book registered: %s", bookDTO.getUuid()));
        } catch (PersonDoesnotExistException | IllegalArgumentException exception) { // CODEREVIEW is FORBIDDEN a good response for an IllegalArgumentException ?
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, exception.getMessage());
        }
    }

    @DeleteMapping(path = "/{uuidLibrarian}/deletebook/{uuidBook}")
    @ResponseStatus(HttpStatus.GONE) // CODEREVIEW GONE is an error code: your user will assume something went wrong. OK (200) is a correct response code
    public void deleteBook(@PathVariable("uuidLibrarian") String uuidLibrarian, @PathVariable("uuidBook") String uuidBook) {
        try {
            personService.findById(uuidLibrarian); // CODEREVIEW this method call does nothing
            this.bookService.deleteBook(uuidLibrarian, uuidBook);
            logger.info(String.format("Book with id %s deleted", uuidBook));
        } catch (PersonDoesnotExistException | BookDoesNotExistException exception) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, exception.getMessage());
        }
    }

    @PatchMapping(path = "/{uuidLibrarian}/undeletebook/{uuidBook}")
    @ResponseStatus(HttpStatus.OK)
    public void undeleteBook(@PathVariable("uuidLibrarian") String uuidLibrarian, @PathVariable("uuidBook") String uuidBook) {
        try {
            personService.findById(uuidLibrarian); // CODEREVIEW this method call does nothing
            this.bookService.unDeleteBook(uuidLibrarian, uuidBook);
            logger.info(String.format("Book with id %s undeleted", uuidBook));
        } catch (PersonDoesnotExistException | BookDoesNotExistException exception) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, exception.getMessage());
        }
    }

    @PutMapping(path = "/{uuidLibrarian}/updatebook/{uuidBook}")
    @ResponseStatus(HttpStatus.OK)
    public void updateBook(@PathVariable("uuidLibrarian") String uuidLibrarian, @PathVariable("uuidBook") String uuidBook, @RequestBody UpdateBookDTO updateBookDTO) {
        try {
            personService.findById(uuidLibrarian);
            bookService.updateBook(uuidLibrarian ,uuidBook, updateBookDTO);
            logger.info(String.format("Book with id %s updated", uuidBook));
        } catch (PersonDoesnotExistException | BookDoesNotExistException | IllegalArgumentException exception) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, exception.getMessage());
        }

    }


    @GetMapping(path = "/{uuidLibrarian}/getbookspermember/{uuidPerson}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<BookDTO> getBooksPerMember(@PathVariable("uuidLibrarian") String uuidLibrarian, @PathVariable("uuidPerson") String uuidPerson) {
        List<BookDTO> booksPerMember = rentalService.findAllBooksPerMember(uuidLibrarian, uuidPerson);
        logger.info(String.format("Books from member with id %s retrieved by librarian with id %s", uuidPerson, uuidLibrarian));
        return booksPerMember;
    }

    @GetMapping(path = "/{uuidLibrarian}/getoverduebooks", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<BookDTO> findAllBooksThatAreOverDue(@PathVariable("uuidLibrarian") String uuidLibrarian) {
        List<BookDTO> booksOverdue = rentalService.findAllBooksThatAreOverDue(uuidLibrarian);
        logger.info(String.format("Books that are overdue retrieved by librarian with id %s", uuidLibrarian));
        return booksOverdue;
    }

}
