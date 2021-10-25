package com.switchfullygroupproject.marsdigibooky.service;

import com.switchfullygroupproject.marsdigibooky.exceptions.RentalException;
import com.switchfullygroupproject.marsdigibooky.mapper.RentalMapper;
import com.switchfullygroupproject.marsdigibooky.repository.BookRepository;
import com.switchfullygroupproject.marsdigibooky.repository.PersonRepository;
import com.switchfullygroupproject.marsdigibooky.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class RentalService {

    private final RentalRepository rentalRepository;
    private final PersonRepository personRepository;
    private final BookRepository bookRepository;
    private final RentalMapper rentalMapper;


    @Autowired
    public RentalService(RentalRepository rentalRepository, PersonRepository personRepository, BookRepository bookRepository, RentalMapper rentalMapper) {
        this.rentalRepository = rentalRepository;
        this.personRepository = personRepository;
        this.bookRepository = bookRepository;
        this.rentalMapper = rentalMapper;
    }


//    public void lendBook(String memberId, String bookId){
//        if(personRepository.contains(memberId) && bookRepository.contains(bookId)){
//            rentalRepository.lendBook(memberId, rentalMapper.toRental(memberId, bookId));
//            bookRepository.updateRentalStatusToTrue(bookId);
//        }
//    }

    ////////////////////////////////////////////////////////////////////////

    public void lendBookV2(String memberId, String bookId) {
        if (!personRepository.contains(memberId)) {
            throw new RentalException("This member id doesn't exist.");
        }
        if (!bookRepository.contains(bookId)) {
            throw new RentalException("This book id doesn't exist.");
        }
        if (bookRepository.getBookById(bookId).isRented()) {
            throw new RentalException("This book is allready rented.");
        }
        rentalRepository.lendBookV2(rentalMapper.toRental(memberId, bookId));
        bookRepository.updateRentalStatusToTrue(bookId);
    }

    public void returnBookV2(String rentalId) {
        if (rentalRepository.getRentalById(rentalId) == null) {
            throw new RentalException("This is not a valid rental id");
        }

        if (rentalRepository.getRentalById(rentalId).getDueDate().isBefore(LocalDate.now())) {
            throw new RentalException("The book you rented is over its due date.");
        }
        rentalRepository.returnBookV2(rentalId);
    }


}
