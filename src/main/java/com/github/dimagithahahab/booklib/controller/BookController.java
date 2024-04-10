package com.github.dimagithahahab.booklib.controller;

import com.github.dimagithahahab.booklib.dto.BookDTO;
import com.github.dimagithahahab.booklib.model.author.Author;
import com.github.dimagithahahab.booklib.model.book.Book;
import com.github.dimagithahahab.booklib.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "lib/books")
public class BookController {
    private final BookService bookService;

    private BookDTO convertToDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setIsbn(book.getIsbn());
        bookDTO.setPublishedDate(book.getPublishedDate());

        BookDTO.AuthorDTO authorDTO = new BookDTO.AuthorDTO();
        authorDTO.setId(book.getAuthor().getId());
        authorDTO.setName(book.getAuthor().getFirstName() + " " + book.getAuthor().getLastName());

        bookDTO.setAuthor(authorDTO);

        return bookDTO;
    }

    private Book convertToEntity(BookDTO bookDTO) {
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setIsbn(bookDTO.getIsbn());
        book.setPublishedDate(bookDTO.getPublishedDate());

        BookDTO.AuthorDTO authorDTO = bookDTO.getAuthor();
        Author author = new Author();
        author.setId(authorDTO.getId());
        book.setAuthor(author);

        return book;
    }


    @GetMapping
    public List<BookDTO> getAllBooks() {
        List<Book> books = bookService.getBooks();
        return books.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<Void> addBook(@RequestBody BookDTO bookDTO) {
        Book book = convertToEntity(bookDTO);

        bookService.addBook(book);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
