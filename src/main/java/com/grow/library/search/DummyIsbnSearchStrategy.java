package com.grow.library.search;

import com.grow.library.model.Book;
import com.grow.library.service.BookService;
import lombok.RequiredArgsConstructor;
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
    }
}
