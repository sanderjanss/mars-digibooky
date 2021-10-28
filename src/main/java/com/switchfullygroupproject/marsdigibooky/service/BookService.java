package com.switchfullygroupproject.marsdigibooky.service;

import com.switchfullygroupproject.marsdigibooky.domain.book.*;
import com.switchfullygroupproject.marsdigibooky.domain.person.Person;
import com.switchfullygroupproject.marsdigibooky.domain.person.User;
import com.switchfullygroupproject.marsdigibooky.exceptions.NoAuthorizationException;
import com.switchfullygroupproject.marsdigibooky.exceptions.PersonDoesnotExistException;
import com.switchfullygroupproject.marsdigibooky.repository.BookRepository;
import com.switchfullygroupproject.marsdigibooky.mapper.BookMapper;
import com.switchfullygroupproject.marsdigibooky.repository.PersonRepository;
import com.switchfullygroupproject.marsdigibooky.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
// CODEREVIEW why is this here?
@ComponentScan(basePackages = "com.switchfullygroupproject.marsdigibooky")
public class BookService {

    private final BookRepository bookRepository;
    private final PersonRepository personRepository;
    private final RentalRepository rentalRepository;
    private final BookMapper bookMapper;

    @Autowired
    public BookService(BookRepository bookRepository, BookMapper bookMapper, RentalRepository rentalRepository, PersonRepository personRepository) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
        this.rentalRepository = rentalRepository;
        this.personRepository = personRepository;
    }

    public List<BookDetailDTO> getAllBooks(String isbnOrNull, String titleOrNull, String authorFirstNameOrNull, String authorLastNameOrNull) {
        return bookMapper.toBookDetailDTO((bookRepository.getAllBooks(isbnOrNull, titleOrNull, authorFirstNameOrNull, authorLastNameOrNull).stream().toList()));
    }

    public BookDetailDTO getBookById(String uuid) {
        return bookMapper.toBookDetailDTO(bookRepository.getBookById(uuid));
    }

    public BookDTO registerBook(String uuid, CreateBookDTO createBookDTO) {
        if(personRepository.findById(uuid).getUser() != User.LIBRARIAN){
            throw new NoAuthorizationException("A book can only be registered by a librarian.");
        }
        Book book = bookRepository.registerBook(this.bookMapper.toBook(createBookDTO));
        return this.bookMapper.toBookDTO(book);
    }

    public void deleteBook(String librarianUuid, String uuidBook) {
        if(personRepository.findById(librarianUuid).getUser() != User.LIBRARIAN){
            throw new NoAuthorizationException("A book can only be deleted by a librarian.");
        }
        this.bookRepository.deleteBook(uuidBook);
    }

    public void unDeleteBook(String uuidLibrarian, String uuidBook) {
        if(personRepository.findById(uuidLibrarian).getUser() != User.LIBRARIAN){
            throw new NoAuthorizationException("A book can only be undeleted by a librarian.");
        }
        this.bookRepository.unDeleteBook(uuidBook);
    }

    public void updateBook(String uuidLibrarian, String uuidBook, UpdateBookDTO updateBookDTO) {
        if(personRepository.findById(uuidLibrarian).getUser() != User.LIBRARIAN){
            throw new NoAuthorizationException("A book can only be updated by a librarian.");
        }
        Book book = this.bookRepository.getBookById(uuidBook);
        Book updatedBook = new Book(book.getUuid(), updateBookDTO.getIsbn(), updateBookDTO.getTitle(), updateBookDTO.getAuthor(), updateBookDTO.getSummary());
        this.bookRepository.updateBook(uuidBook, updatedBook);
    }

    public BookDetailDTOV2 findBookIfRentedReturnBookDetailDTO(String memberId, String bookId){
        if(personRepository.findById(memberId) == null){
            throw new PersonDoesnotExistException("This person id does not exist.");
        }
        Book book = bookRepository.getBookById(bookId);
        if(bookRepository.getBookById(bookId).isRented()){
            Person person = personRepository.findById(rentalRepository.findPersonIdPerBookId(bookId));
            return bookMapper.toBookDetailDTOV2(book, person);
        }

        return bookMapper.toBookDetailDTOV2(book);
    }
}
