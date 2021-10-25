package com.switchfullygroupproject.marsdigibooky.mapper;


import com.switchfullygroupproject.marsdigibooky.domain.book.*;
import com.switchfullygroupproject.marsdigibooky.domain.person.Person;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMapper {

    public List<BookDetailDTO> toBookDetailDTO(List<Book> bookList) {
        return bookList.stream()
                .map(this::toBookDetailDTO)
                .collect(Collectors.toList());
    }

    public List<BookDTO> toBookDTO(List<Book> bookList) {
        return bookList.stream()
                .map(this::toBookDTO)
                .collect(Collectors.toList());
    }


    public BookDTO toBookDTO(Book book) {
        return new BookDTO()
                .setUuid(book.getUuid())
                .setISBN(book.getIsbn())
                .setTitle(book.getTitle())
                .setAuthor(book.getAuthor());
    }

    public Book toBook(CreateBookDTO createBookDTO) {
        return new Book(createBookDTO.getIsbn(), createBookDTO.getTitle(), createBookDTO.getAuthor(), createBookDTO.getSummary());
    }

    public BookDetailDTO toBookDetailDTO(Book book) {
        return new BookDetailDTO()
                .setUuid(book.getUuid())
                .setIsbn(book.getIsbn())
                .setTitle(book.getTitle())
                .setAuthor(book.getAuthor())
                .setSummary(book.getSummary());
    }


    public BookDetailDTOV2 toBookDetailDTOV2(Book book, Person person) {
        return new BookDetailDTOV2()
                .setUuid(book.getUuid())
                .setIsbn(book.getIsbn())
                .setTitle(book.getTitle())
                .setAuthor(book.getAuthor())
                .setSummary(book.getSummary())
                .setRented(book.isRented())
                .setRentedBy(person.getFirstName() + " " + person.getLastName());
    }

}
