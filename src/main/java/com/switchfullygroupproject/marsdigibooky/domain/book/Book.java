package com.switchfullygroupproject.marsdigibooky.domain.book;

import com.switchfullygroupproject.marsdigibooky.domain.author.Author;

import java.util.UUID;

public class Book {


    private final String uuid;
    private final String ISBN;
    private final String title;
    private final Author author;
    private final String summary;

    public Book(String ISBN, String title, Author author, String summary) {
        this.uuid = UUID.randomUUID().toString();
        this.ISBN = ISBN;
        this.title = title;
        this.author = author;
        this.summary = summary;
    }

    public Book(String uuid, String ISBN, String title, Author author, String summary) {
        this.uuid = uuid;
        this.ISBN = ISBN;
        this.title = title;
        this.author = author;
        this.summary = summary;
    }

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

    public String getSummary() {
        return summary;
    }
}
