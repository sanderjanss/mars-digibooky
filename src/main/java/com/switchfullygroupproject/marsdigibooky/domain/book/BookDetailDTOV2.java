package com.switchfullygroupproject.marsdigibooky.domain.book;

import com.switchfullygroupproject.marsdigibooky.domain.author.Author;

// CODEREVIEW not a very descriptive class name
// Why have 3 classes BookDTO, BookDetailDTO and BookDetailDTOV2 ?
// it is more conventional to just have 1 and fill in only the fields that are relevant for your specific controller method
public class BookDetailDTOV2 {

    private String uuid;
    private String isbn;
    private String title;
    private Author author;
    private String summary;
    private boolean isRented;
    private String rentedBy;


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

    public String getRentedBy() {
        return rentedBy;
    }

    public BookDetailDTOV2 setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public BookDetailDTOV2 setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public BookDetailDTOV2 setTitle(String title) {
        this.title = title;
        return this;
    }

    public BookDetailDTOV2 setAuthor(Author author) {
        this.author = author;
        return this;
    }

    public BookDetailDTOV2 setSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public BookDetailDTOV2 setRented(boolean rented) {
        this.isRented = rented;
        return this;
    }

    public BookDetailDTOV2 setRentedBy(String rentedBy) {
        this.rentedBy = rentedBy;
        return this;
    }
}
