package com.switchfullygroupproject.marsdigibooky.mapper;


import com.switchfullygroupproject.marsdigibooky.domain.book.Book;
import com.switchfullygroupproject.marsdigibooky.domain.book.BookDTO;
import com.switchfullygroupproject.marsdigibooky.domain.book.BookDetailDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMapper {

    public List<BookDetailDTO> toBookDTO(List<Book> bookList) {
        return bookList.stream()
                .map(this::toBookDetailDTO)
                .collect(Collectors.toList());
    }

    public BookDTO toBookDTO(Book book) {
        return new BookDTO()
                .setUuid(book.getUuid())
                .setISBN(book.getISBN())
                .setTitle(book.getTitle())
                .setAuthor(book.getAuthor());
    }

    public Book toBook(BookDetailDTO bookDetailDTO) {
        return new Book(bookDetailDTO.getUuid(), bookDetailDTO.getISBN(), bookDetailDTO.getTitle(), bookDetailDTO.getAuthor(), bookDetailDTO.getSummary());
    }

    public BookDetailDTO toBookDetailDTO(Book book) {
        return new BookDetailDTO()
                .setUuid(book.getUuid())
                .setISBN(book.getISBN())
                .setTitle(book.getTitle())
                .setAuthor(book.getAuthor())
                .setSummary(book.getSummary());
    }

}
