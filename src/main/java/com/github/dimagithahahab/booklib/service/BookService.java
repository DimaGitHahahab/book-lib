package com.github.dimagithahahab.booklib.service;

import com.github.dimagithahahab.booklib.exception.AuthorDoesNotExistException;
import com.github.dimagithahahab.booklib.exception.BookAlreadyExistsException;
import com.github.dimagithahahab.booklib.model.book.Book;
import com.github.dimagithahahab.booklib.repository.AuthorRepository;
import com.github.dimagithahahab.booklib.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
