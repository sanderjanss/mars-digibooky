package com.switchfullygroupproject.marsdigibooky.repository;

import com.switchfullygroupproject.marsdigibooky.domain.author.Author;
import com.switchfullygroupproject.marsdigibooky.domain.book.Book;
import com.switchfullygroupproject.marsdigibooky.domain.book.UpdateBookDTO;
import com.switchfullygroupproject.marsdigibooky.domain.person.Person;
import com.switchfullygroupproject.marsdigibooky.domain.person.User;
import com.switchfullygroupproject.marsdigibooky.exceptions.BookDoesNotExistException;
import com.switchfullygroupproject.marsdigibooky.helperclasses.WildCardValidator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
        book1.setRented(true);
        Book book2 = new Book("86c5e7a3-d0e2-48bd-bfdb-b04e0324df5f",
                "846574859675",
                "Het Verdriet van België",
                new Author("Paulo",
                        "Caulo"),
                "Blablabla");
        Book book3 = new Book("86c5e7a3-d0e2-48bd-bfdb-b04e0324df4f",
                "89113256423",
                "Sapiens",
                new Author("Yuval Noah",
                        "Harari"),
                "Blablabla");
        this.booksById.put(book1.getUuid(), book1);
        this.booksById.put(book2.getUuid(), book2);
        this.booksById.put(book3.getUuid(), book3);
    }

    public Collection<Book> getAllBooks(String isbnOrNull, String titleOrNull, String authorFirstNameOrNull, String authorLastNameOrNull) {
        return this.booksById.entrySet().stream()
                .filter(set -> set.getValue().isShowableToUser())
                .filter(set -> isbnOrNull == null || WildCardValidator.match(isbnOrNull, set.getValue().getIsbn()))
                .filter(set -> titleOrNull == null || WildCardValidator.match(titleOrNull, set.getValue().getTitle()))
                .filter(set -> (authorFirstNameOrNull == null || WildCardValidator.match(authorFirstNameOrNull, set.getValue().getAuthor().getFirstName())))
                .filter(set -> (authorLastNameOrNull == null || WildCardValidator.match(authorLastNameOrNull, set.getValue().getAuthor().getLastName())))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    public Book getBookById(String uuid) {
        var foundBook = this.booksById.get(uuid);
        if (foundBook == null || !foundBook.isShowableToUser()) {
            throw new BookDoesNotExistException(String.format("Book with uuid %s not found", uuid));
        }
        return foundBook;
    }

    public List<Book> getBooksByIds(List<String> bookIds) {
        List<Book> bookList = new ArrayList<>();
        for (Map.Entry<String, Book> entry : booksById.entrySet()) {
            for (String bookId : bookIds) {
                if (entry.getValue().getUuid().equals(bookId)) {
                    bookList.add(entry.getValue());
                }
            }
        }
        return bookList;
    }

    public Book getBookByIdAsLibrarian(String uuid) {
        var foundBook = this.booksById.get(uuid);
        if (foundBook == null) {
            throw new BookDoesNotExistException(String.format("Book with uuid %s not found", uuid));
        }
        return foundBook;
    }

    public Book registerBook(Book book) {
        this.booksById.put(book.getUuid(), book);
        return book;
    }

    public void deleteBook(String uuid) {
        var book = getBookByIdAsLibrarian(uuid);
        book.setShowableToUser(false);
    }

    public void unDeleteBook(String uuidBook) {
        var book = getBookByIdAsLibrarian(uuidBook);
        book.setShowableToUser(true);
    }

    public void updateBook(String uuidBook, Book book) {
        booksById.put(uuidBook, book);
    }

    //HELPER METHODS

    public boolean contains(String uuid) {
        return booksById.containsKey(uuid);
    }

    public void updateRentalStatusToTrue(String bookId) {
        booksById.get(bookId).setRented(true);
    }

    public void updateRentalStatusToFalse(String bookId) {
        booksById.get(bookId).setRented(false);
    }


}
