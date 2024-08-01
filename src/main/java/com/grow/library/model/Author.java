package com.grow.library.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class Author extends Person {
    private String pseudonym;
}
