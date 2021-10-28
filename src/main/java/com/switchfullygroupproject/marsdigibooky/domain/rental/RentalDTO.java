package com.switchfullygroupproject.marsdigibooky.domain.rental;

import java.time.LocalDate;
import java.util.UUID;

public class RentalDTO {

    private String rentalId;
    private final String personId;
    private final String bookId;
    private final LocalDate dueDate;

    public RentalDTO(String personId, String bookId, LocalDate dueDate) {
        this.rentalId = UUID.randomUUID().toString();
        this.personId = personId;
        this.bookId = bookId;
        this.dueDate = dueDate.plusWeeks(3);
    }

    // CODEREVIEW unused setter
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
}
