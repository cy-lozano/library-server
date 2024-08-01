package com.grow.library.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Member extends Person {
    private String membershipNumber;
    private String email;
    private String mobileNumber;
    private List<Book> borrowedBooks;
}
