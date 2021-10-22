package com.switchfullygroupproject.marsdigibooky.domain;

import java.time.LocalDate;
import java.util.UUID;

public class RentalDTO {

    private String lendingId;
    private String personId;
    private String bookId;
    private final LocalDate localDate;

    public RentalDTO(String personId, String bookId, LocalDate localDate) {
        this.lendingId = UUID.randomUUID().toString();
        this.personId = personId;
        this.bookId = bookId;
        this.localDate = localDate;
    }

    public void setLendingId(String lendingId) {
        this.lendingId = lendingId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getLendingId() {
        return lendingId;
    }

    public String getPersonId() {
        return personId;
    }

    public String getBookId() {
        return bookId;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }
}
