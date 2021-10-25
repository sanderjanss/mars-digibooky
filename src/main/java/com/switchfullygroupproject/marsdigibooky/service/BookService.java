package com.switchfullygroupproject.marsdigibooky.service;

import com.switchfullygroupproject.marsdigibooky.domain.book.*;
import com.switchfullygroupproject.marsdigibooky.domain.person.Person;
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
        return bookMapper.toBookDTO(bookRepository.getAllBooks(isbnOrNull, titleOrNull, authorFirstNameOrNull, authorLastNameOrNull).stream().toList());
    }

    public BookDetailDTO getBookById(String uuid) {
        return bookMapper.toBookDetailDTO(bookRepository.getBookById(uuid));
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

    public BookDetailDTOV2 findBookIfRentedReturnBookDetailDTO(String memberId, String bookId){
        if(personRepository.findById(memberId) == null){
            throw new PersonDoesnotExistException("This person id does not exist.");
        }
        if(bookRepository.getBookById(bookId).isRented()){
            Person person = personRepository.findById(rentalRepository.findPersonIdPerBookId(bookId));
            Book book = bookRepository.getBookById(bookId);
            return bookMapper.toBookDetailDTOV2(book, person);
        }
        return null;
    }
}
