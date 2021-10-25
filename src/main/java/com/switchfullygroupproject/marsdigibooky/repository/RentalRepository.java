package com.switchfullygroupproject.marsdigibooky.repository;

import com.switchfullygroupproject.marsdigibooky.domain.Rental;
import com.switchfullygroupproject.marsdigibooky.domain.person.Person;
import com.switchfullygroupproject.marsdigibooky.domain.person.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class RentalRepository {

    public final Logger logger = LoggerFactory.getLogger(RentalRepository.class);
    private final Map<String, Rental> rentalPerRentalId;

    public RentalRepository() {
        this.rentalPerRentalId = new ConcurrentHashMap<>();
        Rental rental = new Rental("8d3f0fff-3426-4156-a8bf-8d38e430813f",
                "c340f6c3-014c-4b22-9698-9758f32e6cd1",
                LocalDate.of(2021, 10, 1));
        rental.setRentalId("a85f09be-3e2f-40c5-8325-4721aef0ce2a");
        rentalPerRentalId.put("a85f09be-3e2f-40c5-8325-4721aef0ce2a", rental);
        logger.warn("rental id: " + rental.getRentalId());
    }


    public void lendBook(Rental rental) {
        rentalPerRentalId.put(rental.getRentalId(), rental);
    }

    public void returnBook(String rentalId) {
        rentalPerRentalId.remove(rentalId);
    }

    public Rental getRentalById(String rentalId) {
        return rentalPerRentalId.get(rentalId);
    }

    public List<String> findBookIdsPerUser(String memberId){
        List<String> bookIds = new ArrayList<>();
        for (Map.Entry<String, Rental> entry : rentalPerRentalId.entrySet()) {
            if (entry.getValue().getPersonId().equals(memberId)) {
                bookIds.add(entry.getValue().getBookId());
            }
        }
        return bookIds;
    }

    public List<String> findBookIdsThatAreOverDue(){
        List<String> overDueBooks = new ArrayList<>();
        for (Map.Entry<String, Rental> entry : rentalPerRentalId.entrySet()) {
            if (entry.getValue().getDueDate().isBefore(LocalDate.now())) {
                overDueBooks.add(entry.getValue().getBookId());
            }
        }
        return overDueBooks;
    }
}
