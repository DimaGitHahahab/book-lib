package com.github.dimagithahahab.booklib.util;

import com.github.dimagithahahab.booklib.dto.AuthorDTO;
import com.github.dimagithahahab.booklib.dto.BookDTO;
import com.github.dimagithahahab.booklib.model.author.Author;
import com.github.dimagithahahab.booklib.model.book.Book;

import java.util.stream.Collectors;

public class DTOConverter {
    public static BookDTO convertToDTO(Book book) {
        return BookDTO.builder()
                .id(book.getId())
                .title(book.getTitle())
                .isbn(book.getIsbn())
                .publishedDate(book.getPublishedDate())
                .author(BookDTO.AuthorDTO.builder()
                        .id(book.getAuthor().getId())
                        .name(book.getAuthor().getFirstName() + " " + book.getAuthor().getLastName())
                        .build()).build();
    }

    public static Book convertToEntity(BookDTO bookDTO) {
        return Book.builder()
                .id(bookDTO.getId())
                .title(bookDTO.getTitle())
                .isbn(bookDTO.getIsbn())
                .publishedDate(bookDTO.getPublishedDate())
                .author(Author.builder()
                        .id(bookDTO.getAuthor().getId())
                        .build())
                .build();
    }

    public static AuthorDTO convertToDTO(Author author) {
        AuthorDTO authorDTO = AuthorDTO.builder()
                .id(author.getId())
                .firstName(author.getFirstName())
                .lastName(author.getLastName())
                .build();

        if (author.getBooks() != null) {
            authorDTO.setBooks(
                    author.getBooks()
                            .stream()
                            .map(book -> AuthorDTO.BookDTO.builder()
                                    .id(book.getId())
                                    .title(book.getTitle())
                                    .build())
                            .collect(Collectors.toSet())
            );
        }

        return authorDTO;
    }

    public static Author convertToEntity(AuthorDTO authorDTO) {

        Author author = Author.builder()
                .id(authorDTO.getId())
                .firstName(authorDTO.getFirstName())
                .lastName(authorDTO.getLastName())
                .build();

        if (authorDTO.getBooks() != null) {
            author.setBooks(authorDTO.getBooks().stream().map(bookDTO -> Book.builder()
                    .id(bookDTO.getId())
                    .title(bookDTO.getTitle())
                    .build()).collect(Collectors.toSet()));
        }

        return author;
    }
}
