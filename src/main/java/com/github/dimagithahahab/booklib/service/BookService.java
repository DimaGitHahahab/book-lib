package com.github.dimagithahahab.booklib.service;

import com.github.dimagithahahab.booklib.exception.AuthorDoesNotExistException;
import com.github.dimagithahahab.booklib.exception.BookAlreadyExistsException;
import com.github.dimagithahahab.booklib.exception.BookDoesNotExistException;
import com.github.dimagithahahab.booklib.model.author.Author;
import com.github.dimagithahahab.booklib.model.book.Book;
import com.github.dimagithahahab.booklib.repository.AuthorRepository;
import com.github.dimagithahahab.booklib.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Book getBook(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new BookDoesNotExistException("Book with such ID does not exist."));
    }

    public Book addBook(Book book) {
        if (bookRepository.existsByIsbn(book.getIsbn())) {
            throw new BookAlreadyExistsException("Book with the same ISBN already exists.");
        }

        if (!authorRepository.existsById(book.getAuthor().getId())) {
            throw new AuthorDoesNotExistException("Author with such an ID does not exist.");
        }

        bookRepository.save(book);

        return book;
    }

    public void delete(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new BookDoesNotExistException("Book with such ID does not exist.");
        }

        bookRepository.deleteById(id);
    }

    public Book alterBook(Book book) {
        if (!bookRepository.existsById(book.getId())) {
            throw new BookDoesNotExistException("Book with such ID does not exist.");
        }
        if (!authorRepository.existsById(book.getAuthor().getId())) {
            throw new AuthorDoesNotExistException("Author with such an ID does not exist.");
        }

        bookRepository.save(book);

        return book;
    }

    public Book updateBook(Long id, Map<String, Object> updates) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookDoesNotExistException("Book with such ID does not exist."));

        updates.forEach((key, value) -> {
            switch (key) {
                case "title":
                    book.setTitle((String) value);
                    break;
                case "isbn":
                    book.setIsbn((String) value);
                    break;
                case "publicationDate":
                    book.setPublishedDate((LocalDate) value);
                    break;
                case "author":
                    Author author = authorRepository.findById((Long) value).orElseThrow(() -> new AuthorDoesNotExistException("Author with such ID does not exist."));
                    book.setAuthor(author);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid field to modify: " + key);
            }
        });

        bookRepository.save(book);

        return book;
    }
}
