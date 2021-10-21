package com.switchfullygroupproject.marsdigibooky.service;

import com.switchfullygroupproject.marsdigibooky.domain.book.Book;
import com.switchfullygroupproject.marsdigibooky.domain.book.BookDTO;
import com.switchfullygroupproject.marsdigibooky.domain.book.BookDetailDTO;
import com.switchfullygroupproject.marsdigibooky.repository.BookRepository;
import com.switchfullygroupproject.marsdigibooky.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


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

    public List<BookDetailDTO> getAllBooks(String isbnOrNull, String titleOrNull) {
        return bookMapper.toBookDTO(bookRepository.getAllBooks(isbnOrNull, titleOrNull).stream().toList());
    }

    public BookDetailDTO getBookById(String uuid) {
        return bookMapper.toBookDetailDTO(bookRepository.getBookById(uuid));
    }
}
