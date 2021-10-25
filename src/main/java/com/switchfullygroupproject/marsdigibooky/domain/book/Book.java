package com.switchfullygroupproject.marsdigibooky.domain.book;

import com.switchfullygroupproject.marsdigibooky.domain.author.Author;
import com.switchfullygroupproject.marsdigibooky.helperclasses.ISBN13Validator;

import java.util.UUID;

public class Book {

    private final String uuid;
    private final String isbn;
    private final String title;
    private final Author author;
    private final String summary;
    private boolean isRented;
    private boolean isShowableToUser;

    public Book(String isbn, String title, Author author, String summary) {
        this.uuid = UUID.randomUUID().toString();
        this.isbn = isbnOrNull(isbn);
        this.title = titleNotNull(title);
        this.author = author;
        this.summary = summary;
        this.isShowableToUser = true;
        this.isRented = false;
    }

    public Book(String uuid, String isbn, String title, Author author, String summary) {
        this.uuid = uuid;
        this.isbn = isbnOrNull(isbn);
        this.title = titleNotNull(title);
        this.author = author;
        this.summary = summary;
        this.isShowableToUser = true;
        this.isRented = false;
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

    public boolean isRented() {
        return isRented;
    }

    public boolean isShowableToUser() {
        return isShowableToUser;
    }

    public void setRented(boolean rented) {
        isRented = rented;
    }

    public void setShowableToUser(boolean showableToUser) {
        isShowableToUser = showableToUser;
    }

    private String isbnOrNull(String isbn) {
        if (isbn == null) {
            throw new IllegalArgumentException("ISBN cant be null.");
        }
        if (!ISBN13Validator.isValidISBN(isbn)) {
            throw new IllegalArgumentException("ISBN not valid");
        }
        return isbn;
    }

    private String titleNotNull(String title) {
        if (title == null) {
            throw new IllegalArgumentException("Title cant be null.");
        }
        return title;
    }

}
