package com.grow.library.search;

import com.grow.library.model.Book;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DummyAuthorSearchStrategy implements BookSearchStrategy {

    @Override
    public SearchType getSearchType() {
        return SearchType.AUTHOR;
    }

    @Override
    public List<Book> search(List<Book> books, String keyword) {
        return books.stream()
                .filter(book -> book.getAuthors().stream()
                        .anyMatch(author -> author.getFirstName().toLowerCase().contains(keyword.toLowerCase())
                                || author.getLastName().toLowerCase().contains(keyword.toLowerCase())))
                .toList();
        /*
          SELECT
            b.*,
            a.*
          FROM book_author ba
          INNER JOIN book b ON ba.book_id = b.id
          INNER JOIN author a ON ba.author_id = a.id
          WHERE a.first_name ILIKE '%<keyword>%'
            OR a.last_name ILIKE '%<keyword>%'
        */
    }
}
