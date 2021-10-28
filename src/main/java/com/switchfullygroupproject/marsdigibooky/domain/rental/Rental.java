package com.switchfullygroupproject.marsdigibooky.domain.rental;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Rental {
    private String rentalId; // CODEREVIEW if any field is final, surely it's the ID
    private final String personId;
    private final String bookId;
    private final LocalDate dueDate;

    public Rental(String personId, String bookId, LocalDate dueDate) {
        this.rentalId = UUID.randomUUID().toString();
        this.personId = personId;
        this.bookId = bookId;
        this.dueDate = dueDate.plusWeeks(3);
    }

    // CODEREVIEW this is a horrible bug the rentalId is used in your equals and hashcode implementation!!
    public void setRentalId(String rentalId) {
        this.rentalId = rentalId;
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
