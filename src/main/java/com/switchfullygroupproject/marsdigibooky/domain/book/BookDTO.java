package com.switchfullygroupproject.marsdigibooky.domain.book;

import com.switchfullygroupproject.marsdigibooky.domain.author.Author;

public class BookDTO {

    private String ISBN;
    private String title;
    private Author author;


    public BookDTO(String ISBN, String title, Author author) {
        this.ISBN = ISBN;
        this.title = title;
        this.author = author;

    }

    public String getISBN() {
        return ISBN;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }


}
