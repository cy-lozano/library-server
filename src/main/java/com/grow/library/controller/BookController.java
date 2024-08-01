package com.grow.library.controller;

import com.grow.library.model.Book;
import com.grow.library.search.SearchType;
import com.grow.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public List<Book> getBooks(){
        return bookService.getBooks();
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable("id") Long id){
        return bookService.getBook(id);
    }

    @PostMapping("/search")
    public List<Book> searchBook(@RequestBody Map<SearchType, String> criteria){
        return bookService.searchBooks(criteria);
    }
}
