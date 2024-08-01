package com.grow.library.service;

import com.grow.library.model.Book;
import com.grow.library.repository.BookRepository;
import com.grow.library.search.BookSearchStrategyFactory;
import com.grow.library.search.SearchType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Repository
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final BookSearchStrategyFactory bookSearchStrategyFactory;

    public List<Book> getBooks() {
        return bookRepository.getBooks();
    }

    public Book getBook(Long id) {
        return bookRepository.getBook(id);
    }

    public List<Book> searchBooks(Map<SearchType, String> criteria) {
        List<Book> books = getBooks();
        for (Map.Entry<SearchType, String> entry : criteria.entrySet()) {
            SearchType searchType = entry.getKey();
            String keyword = entry.getValue();
            books = bookSearchStrategyFactory.getBookSearchStrategy(searchType).search(books, keyword);
        }
        return books;
    }

    public Long createBook(Book book) {
        return bookRepository.createBook(book);
    }
}
