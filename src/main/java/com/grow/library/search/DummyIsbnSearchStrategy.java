package com.grow.library.search;

import com.grow.library.model.Book;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DummyIsbnSearchStrategy implements BookSearchStrategy {

    @Override
    public SearchType getSearchType() {
        return SearchType.ISBN;
    }

    @Override
    public List<Book> search(List<Book> books, String keyword) {
        return books.stream()
                .filter(book -> book.getIsbn().equals(keyword))
                .toList();
        /*
          SELECT
            b.*,
            a.*
          FROM book_author ba
          INNER JOIN book b ON ba.book_id = b.id
          INNER JOIN author a ON ba.author_id = a.id
          WHERE b.isbn = <keyword>;
        */
    }
}
