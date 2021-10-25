package com.switchfullygroupproject.marsdigibooky.mapper;

import com.switchfullygroupproject.marsdigibooky.domain.rental.Rental;
import com.switchfullygroupproject.marsdigibooky.domain.rental.RentalDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class RentalMapper {

    public final Logger logger = LoggerFactory.getLogger(RentalMapper.class);

    public Rental toRental(String memberId, String bookId) {
        Rental rental = new Rental(memberId, bookId, LocalDate.now());
        rental.setRentalId(rental.getRentalId());
        logger.warn("rental id: " + rental.getRentalId());
        return rental;

    }

    public RentalDTO toDto(Rental rental) {
        RentalDTO rentalDTO = new RentalDTO(rental.getPersonId(), rental.getBookId(), rental.getDueDate());
        rentalDTO.setRentalId(rental.getRentalId());
        return rentalDTO;
    }
}
