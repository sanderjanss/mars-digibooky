package com.switchfullygroupproject.marsdigibooky.service;

import com.switchfullygroupproject.marsdigibooky.domain.book.Book;
import com.switchfullygroupproject.marsdigibooky.domain.book.BookDTO;
import com.switchfullygroupproject.marsdigibooky.domain.book.BookDetailDTO;
import com.switchfullygroupproject.marsdigibooky.domain.book.CreateBookDTO;
import com.switchfullygroupproject.marsdigibooky.repository.BookRepository;
import com.switchfullygroupproject.marsdigibooky.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@ComponentScan(basePackages = "com.switchfullygroupproject.marsdigibooky")
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Autowired
    public BookService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
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
}
