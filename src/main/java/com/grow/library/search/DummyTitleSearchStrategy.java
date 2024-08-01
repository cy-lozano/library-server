package com.grow.library.search;

import com.grow.library.model.Book;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DummyTitleSearchStrategy implements BookSearchStrategy {

    @Override
    public SearchType getSearchType() {
        return SearchType.TITLE;
    }

    @Override
    public List<Book> search(List<Book> books, String keyword) {
        return books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(keyword.toLowerCase()))
                .toList();
    }
}
