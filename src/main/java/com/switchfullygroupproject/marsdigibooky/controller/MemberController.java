package com.switchfullygroupproject.marsdigibooky.controller;

import com.switchfullygroupproject.marsdigibooky.domain.book.BookDetailDTOV2;
import com.switchfullygroupproject.marsdigibooky.repository.RentalRepository;
import com.switchfullygroupproject.marsdigibooky.service.BookService;
import com.switchfullygroupproject.marsdigibooky.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/members")
public class MemberController {

    private final RentalService rentalService;
    private final BookService bookService;

    @Autowired
    public MemberController(RentalService rentalService, BookService bookService) {
        this.rentalService = rentalService;
        this.bookService = bookService;
    }

    @PostMapping(path = "/{memberId}/lendbook/{bookId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void lendBookV2(@PathVariable String memberId, @PathVariable String bookId){
        rentalService.lendBook(memberId, bookId);
    }

    @PostMapping(path = "/returnbook/{rentalId}")
    @ResponseStatus(HttpStatus.OK)
    public void returnBook(@PathVariable String rentalId){
        rentalService.returnBook(rentalId);
    }


    @GetMapping(path = "/{memberId}/showbookdetails/{bookId}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public BookDetailDTOV2 showBookDetails(@PathVariable String memberId, @PathVariable String bookId){
        return bookService.findBookIfRentedReturnBookDetailDTO(memberId, bookId);
    }
}
