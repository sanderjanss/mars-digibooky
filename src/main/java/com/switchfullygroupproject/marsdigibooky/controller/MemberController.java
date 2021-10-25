package com.switchfullygroupproject.marsdigibooky.controller;

import com.switchfullygroupproject.marsdigibooky.domain.book.BookDetailDTOV2;
import com.switchfullygroupproject.marsdigibooky.service.BookService;
import com.switchfullygroupproject.marsdigibooky.service.RentalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public void lendBookV2(@PathVariable String memberId, @PathVariable String bookId) {
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
        BookDetailDTOV2 bookDetail = bookService.findBookIfRentedReturnBookDetailDTO(memberId, bookId);
        logger.info(String.format("Bookdetails with id %s from member with id %s showed.", bookId, memberId));
        return bookDetail;
    }
}
