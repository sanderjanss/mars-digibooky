package com.switchfullygroupproject.marsdigibooky.controller;

import com.switchfullygroupproject.marsdigibooky.domain.book.Book;
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
import org.springframework.web.bind.annotation.*;
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
            personService.findById(uuid);
            BookDTO bookDTO = this.bookService.registerBook(createBookDTO);
            logger.info(String.format("Book registered: %s", bookDTO.getUuid()));
        } catch (PersonDoesnotExistException | IllegalArgumentException exception) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, exception.getMessage());
        }
    }

    @DeleteMapping(path = "/{uuidLibrarian}/deletebook/{uuidBook}")
    @ResponseStatus(HttpStatus.GONE)
    public void deleteBook(@PathVariable("uuidLibrarian") String uuidLibrarian, @PathVariable("uuidBook") String uuidBook) {
        try {
            personService.findById(uuidLibrarian);
            this.bookService.deleteBook(uuidBook);
            logger.info(String.format("Book with id %s deleted", uuidBook));
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
            logger.info(String.format("Book with id %s undeleted", uuidBook));
        } catch (PersonDoesnotExistException | BookDoesNotExistException exception) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, exception.getMessage());
        }
    }

    @PutMapping(path = "/{uuidLibrarian}/updatebook/{uuidBook}")
    @ResponseStatus(HttpStatus.OK)
    public void updateBook(@PathVariable("uuidLibrarian") String uuidLibrarian, @PathVariable("uuidBook") String uuidBook, @RequestBody UpdateBookDTO updateBookDTO){
        try {
            personService.findById(uuidLibrarian);
            bookService.updateBook(uuidBook, updateBookDTO);
            logger.info(String.format("Book with id %s updated", uuidBook));
        } catch (PersonDoesnotExistException | BookDoesNotExistException | IllegalArgumentException exception) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, exception.getMessage());
        }

    }


    @GetMapping(path = "/{uuidLibrarian}/getbookspermember/{uuidPerson}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<BookDTO> getBooksPerMember(@PathVariable("uuidLibrarian") String uuidLibrarian, @PathVariable("uuidPerson") String uuidPerson){
        return rentalService.findAllBooksPerMember(uuidLibrarian, uuidPerson);
    }

    @GetMapping(path = "/{uuidLibrarian}/getoverduebooks", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<BookDTO> findAllBooksThatAreOverDue(@PathVariable("uuidLibrarian")String uuidLibrarian){
        return rentalService.findAllBooksThatAreOverDue(uuidLibrarian);
    }

}
