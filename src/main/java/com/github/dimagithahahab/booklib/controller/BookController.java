package com.github.dimagithahahab.booklib.controller;

import com.github.dimagithahahab.booklib.dto.BookDTO;
import com.github.dimagithahahab.booklib.model.book.Book;
import com.github.dimagithahahab.booklib.service.book.BookService;
import com.github.dimagithahahab.booklib.util.DTOConverter;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.github.dimagithahahab.booklib.util.DTOConverter.convertToEntity;


@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/lib/books")
@Validated
public class BookController {
    private final BookService bookService;

    @GetMapping
    @Operation(summary = "List all the books", tags = {"book"})
    @ResponseStatus(HttpStatus.OK)
    public List<BookDTO> getAllBooks() {
        List<Book> books = bookService.getBooks();
        return books.stream().map(DTOConverter::convertToDTO).collect(Collectors.toList());
    }

    @GetMapping(path = "{id}")
    @Operation(summary = "Get a book by its id", tags = {"book"})
    @ResponseStatus(HttpStatus.OK)
    public BookDTO getBook(@PathVariable("id") Long id) {
        Book book = bookService.getBook(id);
        return DTOConverter.convertToDTO(book);
    }

    @PostMapping
    @Operation(summary = "Add a new book", tags = {"book"})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BookDTO> addBook(@Valid @RequestBody BookDTO bookDTO) {
        Book book = convertToEntity(bookDTO);

        Book savedBook = bookService.addBook(book);
        BookDTO savedBookDTO = DTOConverter.convertToDTO(savedBook);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedBookDTO);
    }

    @DeleteMapping(path = "{id}")
    @Operation(summary = "Delete a book by its id", tags = {"book"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteBook(@PathVariable("id") Long id) {
        bookService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping(path = "{id}")
    @Operation(summary = "Update a book by its id", tags = {"book"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BookDTO> updateBook(@PathVariable("id") Long id, @Valid @RequestBody BookDTO bookDTO) {
        Book book = convertToEntity(bookDTO);
        book.setId(id);

        Book savedBook = bookService.alterBook(book);
        BookDTO savedBookDTO = DTOConverter.convertToDTO(savedBook);

        return ResponseEntity.status(HttpStatus.OK).body(savedBookDTO);
    }

    @PatchMapping(path = "{id}")
    @Operation(summary = "Update a book by its id", tags = {"book"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BookDTO> patchBook(@PathVariable("id") Long id, @Valid @RequestBody Map<String, Object> updates) {
        Book savedBook = bookService.updateBook(id, updates);
        BookDTO savedBookDTO = DTOConverter.convertToDTO(savedBook);

        return ResponseEntity.status(HttpStatus.OK).body(savedBookDTO);
    }


}
