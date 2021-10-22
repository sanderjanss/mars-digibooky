package com.switchfullygroupproject.marsdigibooky.mapper;

import com.switchfullygroupproject.marsdigibooky.domain.Rental;
import com.switchfullygroupproject.marsdigibooky.domain.RentalDTO;
import com.switchfullygroupproject.marsdigibooky.domain.person.Person;
import com.switchfullygroupproject.marsdigibooky.domain.person.PersonDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class RentalMapper {

    public Rental toRental(String memberId, String bookId) {
        Rental rental = new Rental(memberId, bookId, LocalDate.now().plusWeeks(3));
        rental.setLendingId(rental.getLendingId());
        return rental;

    }

    public RentalDTO toDto(Rental rental) {
        RentalDTO rentalDTO = new RentalDTO(rental.getPersonId(), rental.getBookId(), rental.getLocalDate());
        rentalDTO.setLendingId(rental.getLendingId());
        return rentalDTO;
    }
}
