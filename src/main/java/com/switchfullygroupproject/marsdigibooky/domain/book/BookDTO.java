package com.switchfullygroupproject.marsdigibooky.domain.book;

import com.switchfullygroupproject.marsdigibooky.domain.author.Author;

public class BookDTO {

    private String uuid;
    private String isbn;
    private String title;
    private Author author;

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

    // CODEREVIEW fluent setters are very uncommon on actual classes. It's better to include a Builder
    public BookDTO setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public BookDTO setISBN(String isbn) {
        this.isbn = isbn;
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
