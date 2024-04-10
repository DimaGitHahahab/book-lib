package com.github.dimagithahahab.booklib.model.book;

import com.github.dimagithahahab.booklib.model.author.Author;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Table
@Entity
public class Book {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @Column(unique = true)
    private String isbn;

    @Temporal(TemporalType.DATE)
    private LocalDate publishedDate;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;
}