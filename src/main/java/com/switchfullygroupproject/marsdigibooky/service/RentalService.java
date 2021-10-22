package com.switchfullygroupproject.marsdigibooky.service;

import com.switchfullygroupproject.marsdigibooky.mapper.RentalMapper;
import com.switchfullygroupproject.marsdigibooky.repository.BookRepository;
import com.switchfullygroupproject.marsdigibooky.repository.PersonRepository;
import com.switchfullygroupproject.marsdigibooky.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    public void lendBook(String memberId, String bookId){
        //STILL NEED TO ADD IF BOOK IS LEND OR NOT bookRepository.findById(bookId).getLendStatus == true -> FIELD NEED TO BE MADE
        // + SET TO TRUE
        if(personRepository.contains(memberId)){
            rentalRepository.lendBook(memberId, rentalMapper.toRental(memberId, bookId));
        }

    }
}
