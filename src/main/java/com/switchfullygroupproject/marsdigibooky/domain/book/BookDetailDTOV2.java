package com.switchfullygroupproject.marsdigibooky.domain.book;

import com.switchfullygroupproject.marsdigibooky.domain.author.Author;
import com.switchfullygroupproject.marsdigibooky.domain.person.Person;

public class BookDetailDTOV2 {

    private  String uuid;
    private  String isbn;
    private  String title;
    private  Author author;
    private  String summary;
    private Person person;



    public String getUuid() {
        return uuid;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public String getSummary() {
        return summary;
    }

    public Person getPerson() {
        return person;
    }

    public BookDetailDTOV2 setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public BookDetailDTOV2 setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public BookDetailDTOV2 setTitle(String title) {
        this.title = title;
        return this;
    }

    public BookDetailDTOV2 setAuthor(Author author) {
        this.author = author;
        return this;
    }

    public BookDetailDTOV2 setSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public BookDetailDTOV2 setPerson(Person person) {
        this.person = person;
        return this;
    }
}
