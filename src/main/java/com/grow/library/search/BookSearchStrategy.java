package com.grow.library.search;

import com.grow.library.model.Book;

import java.util.List;

public interface BookSearchStrategy {
    SearchType getSearchType();

    List<Book> search(List<Book> books, String keyword);
}
