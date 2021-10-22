package com.switchfullygroupproject.marsdigibooky.domain;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Rental {
    private String lendingId;
    private String personId;
    private String bookId;
    private final LocalDate localDate;

    public Rental(String personId, String bookId, LocalDate localDate) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rental rental = (Rental) o;
        return Objects.equals(lendingId, rental.lendingId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lendingId);
    }
}
