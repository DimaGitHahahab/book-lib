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
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public void addBook(Book book) {
        if (bookRepository.existsByIsbn(book.getIsbn())) {
            throw new BookAlreadyExistsException("Book with the same ISBN already exists.");
        }

        if (!authorRepository.existsById(book.getAuthor().getId())) {
            throw new AuthorDoesNotExistException("Author with such an ID does not exist.");
        }

        bookRepository.save(book);
    }

    public void delete(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new BookDoesNotExistException("Book with such ID does not exist.");
        }

        bookRepository.deleteById(id);
    }

    public void alterBook(Book book) {
        if (!bookRepository.existsById(book.getId())) {
            throw new BookDoesNotExistException("Book with such ID does not exist.");
        }
        if (!authorRepository.existsById(book.getAuthor().getId())) {
            throw new AuthorDoesNotExistException("Author with such an ID does not exist.");
        }

        bookRepository.save(book);
    }

    public void updateBook(Long id, Map<String, Object> updates) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookDoesNotExistException("Book with such ID does not exist."));

        if (updates.containsKey("title")) {
            book.setTitle((String) updates.get("title"));
        }
        if (updates.containsKey("isbn")) {
            book.setIsbn((String) updates.get("isbn"));
        }
        if (updates.containsKey("publishedDate")) {
            book.setPublishedDate((LocalDate) updates.get("publishedDate"));
        }
        if (updates.containsKey("author")) {
            Long authorId = (Long) updates.get("author");
            if (!authorRepository.existsById(authorId)) {
                throw new AuthorDoesNotExistException("Author with such an ID does not exist.");
            }

            Optional<Author> author = authorRepository.findById(authorId);
            author.ifPresent(book::setAuthor);
        }

        bookRepository.save(book);
    }
}
