package com.switchfullygroupproject.marsdigibooky.domain.book;

import com.switchfullygroupproject.marsdigibooky.domain.author.Author;

public class Book {

    private String ISBN;
    private String title;
    private Author author;
    private String summary;

    public Book(String ISBN, String title, Author author, String summary) {
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
