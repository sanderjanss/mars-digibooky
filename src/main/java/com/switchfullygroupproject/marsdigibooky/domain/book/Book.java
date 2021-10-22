package com.switchfullygroupproject.marsdigibooky.domain.book;

import com.switchfullygroupproject.marsdigibooky.domain.author.Author;

import java.util.UUID;

public class Book {

    private final String uuid;
    private final String isbn;
    private final String title;
    private final Author author;
    private final String summary;
    private boolean isShowableToUser;

    public Book(String isbn, String title, Author author, String summary) {
        this.uuid = UUID.randomUUID().toString();
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.summary = summary;
        this.isShowableToUser = true;
    }

    public Book(String uuid, String isbn, String title, Author author, String summary) {
        this.uuid = uuid;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.summary = summary;
        this.isShowableToUser = true;
    }

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

    public boolean isShowableToUser() {
        return isShowableToUser;
    }

    public void setShowableToUser(boolean showableToUser) {
        isShowableToUser = showableToUser;
    }
}
