package com.grow.library.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class Book {
    private Long id;
    private String isbn;
    private String title;
    private String category;
    private String publisher;
    private LocalDate publishedDate;
    private Integer copies;
    private Integer availableCopies;
    private List<Author> authors;

    public boolean isAvailable() {
        return availableCopies > 0;
    }
}
