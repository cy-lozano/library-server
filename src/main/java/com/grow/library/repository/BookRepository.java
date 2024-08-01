package com.grow.library.repository;

import com.grow.library.model.Book;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository {
    List<Book> getBooks();

    Book getBook(Long id);

    Long createBook(Book book);
}
