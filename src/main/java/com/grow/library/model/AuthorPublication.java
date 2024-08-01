package com.grow.library.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AuthorPublication extends Author {
    List<Book> books;
}
