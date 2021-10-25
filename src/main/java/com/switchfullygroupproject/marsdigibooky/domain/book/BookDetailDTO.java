package com.switchfullygroupproject.marsdigibooky.domain.book;

import com.switchfullygroupproject.marsdigibooky.domain.author.Author;

public class BookDetailDTO {

    private  String uuid;
    private  String isbn;
    private String title;
    private Author author;
    private String summary;


    public String getUuid() {
        return uuid;
    }

    public String getIsbn() {
        return isbn;
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

    public BookDetailDTO setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public BookDetailDTO setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public BookDetailDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public BookDetailDTO setAuthor(Author author) {
        this.author = author;
        return this;
    }

    public BookDetailDTO setSummary(String summary) {
        this.summary = summary;
        return this;
    }


}
