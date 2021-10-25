package com.switchfullygroupproject.marsdigibooky.repository;

import com.switchfullygroupproject.marsdigibooky.domain.Rental;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class RentalRepository {

    //    private final Map<String, List<Rental>> rentalsPerUser;
    private final Map<String, Rental> rentalPerRentalId;

    public RentalRepository() {
        this.rentalPerRentalId = new ConcurrentHashMap<>();
//        this.rentalsPerUser = new ConcurrentHashMap<>();
    }

//    public void lendBook(String memberId, Rental rental){
//        rentalsPerUser.get(memberId).add(rental);
//        rentalsPerUser.put(memberId, rentalsPerUser.get(memberId));
//    }
//
//    public void returnBook(String memberId, Rental rental){
//        List<Rental> memberList = new ArrayList<>();
//        int i = 0;
//        for (Map.Entry<String, List<Rental>> entry : rentalsPerUser.entrySet()) {
//
//            if (entry.getValue().get(i).equals(rental)){
//                entry.getValue().remove(rental);
//            }
//            i++;
//            // THIS WILL BREAK BECAUSE WE NEED AN ITERATOR PROBABLY
//        }
//    }

    /////////////////////////////////////////////////////////////////////////////

    public void lendBookV2(Rental rental) {
        rentalPerRentalId.put(rental.getRentalId(), rental);
    }

    public void returnBookV2(String rentalId) {
        rentalPerRentalId.remove(rentalId);
    }

    public Rental getRentalById(String rentalId) {
        return rentalPerRentalId.get(rentalId);
    }
}
