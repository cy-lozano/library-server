package com.grow.library.search;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class BookSearchStrategyFactory {
    private final Map<SearchType, BookSearchStrategy> bookSearchStrategyMap;

    public BookSearchStrategyFactory(List<BookSearchStrategy> bookSearchStrategyList) {
        this.bookSearchStrategyMap = bookSearchStrategyList.stream()
                .collect(Collectors.toMap(BookSearchStrategy::getSearchType, Function.identity()));
    }

    public BookSearchStrategy getBookSearchStrategy(SearchType searchType) {
        return bookSearchStrategyMap.get(searchType);
    }
}
