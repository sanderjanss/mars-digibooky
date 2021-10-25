package com.switchfullygroupproject.marsdigibooky.domain;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Rental {
    private String rentalId;
    private String personId;
    private String bookId;
    private final LocalDate dueDate;

    public Rental(String personId, String bookId, LocalDate dueDate) {
        this.rentalId = UUID.randomUUID().toString();
        this.personId = personId;
        this.bookId = bookId;
        this.dueDate = dueDate;
    }

    public void setRentalId(String rentalId) {
        this.rentalId = rentalId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getRentalId() {
        return rentalId;
    }

    public String getPersonId() {
        return personId;
    }

    public String getBookId() {
        return bookId;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rental rental = (Rental) o;
        return Objects.equals(rentalId, rental.rentalId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rentalId);
    }
}
