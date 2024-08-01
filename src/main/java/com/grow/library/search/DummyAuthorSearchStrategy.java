package com.grow.library.search;

import com.grow.library.model.Book;
import com.grow.library.service.BookService;
import lombok.RequiredArgsConstructor;
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
    }
}
