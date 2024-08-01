package com.grow.library.repository.impl;

import com.grow.library.model.Author;
import com.grow.library.model.Book;
import com.grow.library.repository.BookRepository;
import net.datafaker.Faker;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Repository
public class DummyBookRepository implements BookRepository {
    private Map<Long, Book> books = new HashMap<>();

    public DummyBookRepository(){
        initDummyBooks();
    }

    private void initDummyBooks() {
        Faker faker = new Faker();

        List<Author> authors = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Author author = new Author();
            author.setId((long) i);
            author.setFirstName(faker.name().firstName());
            author.setLastName(faker.name().lastName());
            author.setPseudonym(faker.name().username());
            authors.add(author);
        }

        for (int i = 1; i <= 100; i++) {
            Book book = new Book();
            book.setId((long) i);
            book.setTitle(faker.book().title());
            book.setIsbn("ISBN".concat(String.valueOf(i)));
            book.setCategory(faker.book().genre());
            book.setPublisher(faker.book().publisher());
            book.setPublishedDate(faker.date().past(365, TimeUnit.DAYS).toLocalDateTime().toLocalDate());
            book.setCopies(faker.number().numberBetween(1, 10));//max 10
            book.setAvailableCopies(faker.number().numberBetween(1, book.getCopies()+1));//should always be <= copies
            book.setAuthors(List.of(authors.get(faker.number().numberBetween(0, authors.size()))));
            books.put(book.getId(), book);
        }
    }

    @Override
    public List<Book> getBooks() {
        return books.values().stream().toList();
    }

    @Override
    public Book getBook(Long id) {
        return books.get(id);
    }
}
