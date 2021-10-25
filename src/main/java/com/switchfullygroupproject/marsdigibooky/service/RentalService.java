package com.switchfullygroupproject.marsdigibooky.service;


import com.switchfullygroupproject.marsdigibooky.domain.book.Book;
import com.switchfullygroupproject.marsdigibooky.domain.person.User;
import com.switchfullygroupproject.marsdigibooky.exceptions.NoAuthorizationException;
import com.switchfullygroupproject.marsdigibooky.exceptions.PersonDoesnotExistException;
import com.switchfullygroupproject.marsdigibooky.exceptions.RentalException;
import com.switchfullygroupproject.marsdigibooky.mapper.RentalMapper;
import com.switchfullygroupproject.marsdigibooky.repository.BookRepository;
import com.switchfullygroupproject.marsdigibooky.repository.PersonRepository;
import com.switchfullygroupproject.marsdigibooky.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public void lendBook(String memberId, String bookId) {
        if (!personRepository.contains(memberId)) {
            throw new RentalException("This member id doesn't exist.");
        }
        if (!bookRepository.contains(bookId)) {
            throw new RentalException("This book id doesn't exist.");
        }
        if (bookRepository.getBookById(bookId).isRented()) {
            throw new RentalException("This book is allready rented.");
        }
        rentalRepository.lendBook(rentalMapper.toRental(memberId, bookId));
        bookRepository.updateRentalStatusToTrue(bookId);
    }

    public void returnBook(String rentalId) {
        if (rentalRepository.getRentalById(rentalId) == null) {
            throw new RentalException("This is not a valid rental id");
        }
        if (rentalRepository.getRentalById(rentalId).getDueDate().isBefore(LocalDate.now())) {
            throw new RentalException("The book you rented is over its due date.");
        }
        String bookId = rentalRepository.getRentalById(rentalId).getBookId();
        rentalRepository.returnBook(rentalId);
        bookRepository.updateRentalStatusToFalse(bookId);
    }

    public List<Book> findAllBooksPerMember(String librarianId, String memberId){
        if (personRepository.findById(librarianId) == null) {
            throw new PersonDoesnotExistException("This librarian Id does not exist.");
        }
        if (personRepository.findById(memberId) == null) {
            throw new PersonDoesnotExistException("This member Id does not exist.");
        }
        if (personRepository.findById(librarianId).getUser() != User.LIBRARIAN) {
            throw new NoAuthorizationException("No authorization, this id does not belong to a librarian.");
        }
        if (personRepository.findById(memberId).getUser() != User.MEMBER) {
            throw new NoAuthorizationException("No authorization, this id does not belong to a member.");
        }
        return bookRepository.getBooksByIds(rentalRepository.findBookIdsPerUser(memberId));
    }

    public List<Book> findAllBooksThatAreOverDue(String librarianId){
        if (personRepository.findById(librarianId) == null) {
            throw new PersonDoesnotExistException("This librarian Id does not exist.");
        }
        if (personRepository.findById(librarianId).getUser() != User.LIBRARIAN) {
            throw new NoAuthorizationException("No authorization, this id does not belong to a librarian.");
        }
        return bookRepository.getBooksByIds(rentalRepository.findBookIdsThatAreOverDue());
    }




}
