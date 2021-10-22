package com.switchfullygroupproject.marsdigibooky.repository;

import com.switchfullygroupproject.marsdigibooky.domain.author.Author;
import com.switchfullygroupproject.marsdigibooky.domain.book.Book;
import com.switchfullygroupproject.marsdigibooky.domain.book.BookDTO;
import com.switchfullygroupproject.marsdigibooky.domain.book.BookDetailDTO;
import com.switchfullygroupproject.marsdigibooky.exceptions.BookDoesNotExistException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
public class BookRepository {

    private ConcurrentHashMap<String, Book> booksById;

    public BookRepository() {
        this.booksById = new ConcurrentHashMap<>();

        //TESTDATA
        Book book1 = new Book("c340f6c3-014c-4b22-9698-9758f32e6cd1",
                "9475640394564",
                "De Alchemist",
                new Author("Paulo", "Caulo"),
                "Blablabla");
        Book book2 = new Book( "86c5e7a3-d0e2-48bd-bfdb-b04e0324df5f",
                "846574859675",
                "Het Verdriet van België",
                new Author("Paulo",
                        "Caulo"),
                "Blablabla");
        this.booksById.put(book1.getUuid(), book1);
        this.booksById.put(book2.getUuid(), book2);
    }

    public Collection<Book> getAllBooks(String isbnOrNull, String titleOrNull, String authorFirstNameOrNull, String authorLastNameOrNull) {
        return this.booksById.entrySet().stream()
                .filter(set -> isbnOrNull == null || set.getValue().getISBN().equals(isbnOrNull))
                .filter(set -> titleOrNull == null || set.getValue().getTitle().equalsIgnoreCase(titleOrNull))
                .filter(set -> authorFirstNameOrNull == null || set.getValue().getAuthor().getFirstName().equalsIgnoreCase(authorFirstNameOrNull))
                .filter(set -> authorLastNameOrNull == null || set.getValue().getAuthor().getLastName().equalsIgnoreCase(authorLastNameOrNull))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    public Book getBookById(String uuid){
        var foundBook = this.booksById.get(uuid);
        if(foundBook == null) {
            throw new BookDoesNotExistException(String.format("Book with uuid %s not found", uuid));
        }
        return foundBook;
    }
}
