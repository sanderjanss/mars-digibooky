package com.switchfullygroupproject.marsdigibooky.domain.book;

import com.switchfullygroupproject.marsdigibooky.domain.author.Author;

public class BookDTO {

    private String uuid;
    private String ISBN;
    private String title;
    private Author author;

    public String getUuid() {
        return uuid;
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

    public BookDTO setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public BookDTO setISBN(String ISBN) {
        this.ISBN = ISBN;
        return this;
    }

    public BookDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public BookDTO setAuthor(Author author) {
        this.author = author;
        return this;
    }
}
