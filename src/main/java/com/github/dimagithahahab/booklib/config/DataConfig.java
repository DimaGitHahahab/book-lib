package com.github.dimagithahahab.booklib.config;

import com.github.dimagithahahab.booklib.model.author.Author;
import com.github.dimagithahahab.booklib.model.book.Book;
import com.github.dimagithahahab.booklib.repository.AuthorRepository;
import com.github.dimagithahahab.booklib.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class DataConfig {

    @Bean
    CommandLineRunner commandLineRunner(AuthorRepository authorRepository, BookRepository bookRepository) {
        return args -> {
            Author author1 = Author.builder()
                    .firstName("John")
                    .lastName("Doe")
                    .build();

            Book book1 = Book.builder()
                    .title("Book 1")
                    .author(author1)
                    .isbn("123-4567890123")
                    .build();


            Book book2 = Book.builder()
                    .title("Book 2")
                    .author(author1)
                    .publishedDate(LocalDate.of(2004,2,17))
                    .build();

            author1.setBooks(new HashSet<>(Set.of(book1, book2)));

            authorRepository.save(author1);
            bookRepository.saveAll(Set.of(book1, book2));

        };
    }
}
