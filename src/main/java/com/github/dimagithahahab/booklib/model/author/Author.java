package com.github.dimagithahahab.booklib.model.author;

import com.github.dimagithahahab.booklib.model.book.Book;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue
    private Long id;

    private String firstName;
    private String lastName;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private Set<Book> books;
}
