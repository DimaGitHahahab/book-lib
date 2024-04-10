package com.github.dimagithahahab.booklib.repository;

import com.github.dimagithahahab.booklib.model.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    boolean existsByIsbn(String isbn);
}
