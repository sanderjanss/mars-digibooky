package com.switchfullygroupproject.marsdigibooky.service;

import com.switchfullygroupproject.marsdigibooky.domain.book.*;
import com.switchfullygroupproject.marsdigibooky.domain.person.Person;
import com.switchfullygroupproject.marsdigibooky.domain.person.PersonDTO;
import com.switchfullygroupproject.marsdigibooky.repository.BookRepository;
import com.switchfullygroupproject.marsdigibooky.mapper.BookMapper;
import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@ComponentScan(basePackages = "com.switchfullygroupproject.marsdigibooky")
public class BookService {

    private final BookRepository bookRepository;
    private final RentalService rentalService;
    private final PersonService personService;
    private final BookMapper bookMapper;

    @Autowired
    public BookService(BookRepository bookRepository, PersonService personService, RentalService rentalService, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.personService = personService;
        this.rentalService = rentalService;
        this.bookMapper = bookMapper;
    }

    public List<BookDetailDTO> getAllBooks(String isbnOrNull, String titleOrNull, String authorFirstNameOrNull, String authorLastNameOrNull) {
        return bookMapper.toBookDTO(bookRepository.getAllBooks(isbnOrNull, titleOrNull, authorFirstNameOrNull, authorLastNameOrNull).stream().toList());
    }


    public BookDTO registerBook(CreateBookDTO createBookDTO) {
        Book book = bookRepository.registerBook(this.bookMapper.toBook(createBookDTO));
        return this.bookMapper.toBookDTO(book);
    }

    public void deleteBook(String uuidBook) {
        this.bookRepository.deleteBook(uuidBook);
    }

    public void unDeleteBook(String uuidBook) {
        this.bookRepository.unDeleteBook(uuidBook);
    }

    public void updateBook(String uuidBook, UpdateBookDTO updateBookDTO) {
        Book book = this.bookRepository.getBookById(uuidBook);
        Book updatedBook = new Book(book.getUuid(), book.getIsbn(), updateBookDTO.getTitle(), updateBookDTO.getAuthor(), updateBookDTO.getSummary());
        this.bookRepository.updateBook(uuidBook, updatedBook);
    }
}
