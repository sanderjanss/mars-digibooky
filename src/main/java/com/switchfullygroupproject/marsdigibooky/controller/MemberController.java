package com.switchfullygroupproject.marsdigibooky.controller;

import com.switchfullygroupproject.marsdigibooky.domain.book.BookDetailDTOV2;
import com.switchfullygroupproject.marsdigibooky.domain.person.PersonDTO;
import com.switchfullygroupproject.marsdigibooky.service.BookService;
import com.switchfullygroupproject.marsdigibooky.service.RentalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/members")
public class MemberController {

    private final RentalService rentalService;
    private final BookService bookService;
    public final Logger logger = LoggerFactory.getLogger(MemberController.class);

    @Autowired
    public MemberController(RentalService rentalService, BookService bookService) {
        this.rentalService = rentalService;
        this.bookService = bookService;
    }

    @PostMapping(path = "/{memberId}/lendbook/{bookId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void lendBookV2(@PathVariable String memberId, @PathVariable String bookId) { // CODEREVIEW what a strange method name
        rentalService.lendBook(memberId, bookId);
        logger.info(String.format("Book with id %s, rented by member with id %s", bookId, memberId));
    }

    @PostMapping(path = "/returnbook/{rentalId}")
    @ResponseStatus(HttpStatus.OK)
    public void returnBook(@PathVariable String rentalId) {
        rentalService.returnBook(rentalId);
        logger.info(String.format("Rental with id %s returned", rentalId));
    }


    @GetMapping(path = "/{memberId}/showbookdetails/{bookId}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public BookDetailDTOV2 showBookDetails(@PathVariable String memberId, @PathVariable String bookId) {
        // CODEREVIEW there's no real value to include information in the method name about "ifRented"
        // also it doesn not really clarify on its own what is meant. I initially interpreted it as: if the book is not rented, this method return nothing
        BookDetailDTOV2 bookDetail = bookService.findBookIfRentedReturnBookDetailDTO(memberId, bookId);
        logger.info(String.format("Bookdetails with id %s from member with id %s showed.", bookId, memberId));
        return bookDetail;
    }

    @GetMapping(path = "/{id}/allmembers", produces = "application/json")
    public List<PersonDTO> findAllMembers(@PathVariable String id) {
      throw new ResponseStatusException(HttpStatus.FORBIDDEN," Only admin can see all members!");
    }

    @PostMapping(path = "/{id}/registeradminorlibrarian", produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerAdminOrLibrarian(@RequestBody PersonDTO personDTO, @PathVariable String id) {
        throw new ResponseStatusException(HttpStatus.FORBIDDEN," Only admin can register a librarian or an admin!");
    }
}
