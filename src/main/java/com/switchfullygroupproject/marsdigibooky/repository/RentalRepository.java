package com.switchfullygroupproject.marsdigibooky.repository;

import com.switchfullygroupproject.marsdigibooky.domain.Rental;
import com.switchfullygroupproject.marsdigibooky.domain.person.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class RentalRepository {

    private final Map<String, List<Rental>> rentalsPerUser;

    public RentalRepository() {
        this.rentalsPerUser = new ConcurrentHashMap<>();
    }

    public void lendBook(String memberId, Rental rental){
        rentalsPerUser.get(memberId).add(rental);
        rentalsPerUser.put(memberId, rentalsPerUser.get(memberId));
    }

    public void returnBook(String memberId, Rental rental){
//        List<Rental> memberList = new ArrayList<>();
//        int i = 0;
//        for (Map.Entry<String, List<Rental>> entry : rentalsPerUser.entrySet()) {
//            if (entry.getValue().get(i).equals(rental)){
//                entry.getValue().remove(rental);
//            }
//            i++;
//            // THIS WILL BREAK BECAUSE WE NEED AN ITERATOR PROBABLY
//        }
    }
}
