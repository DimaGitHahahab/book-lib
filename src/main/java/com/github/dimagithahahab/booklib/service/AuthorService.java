package com.github.dimagithahahab.booklib.service;

import com.github.dimagithahahab.booklib.exception.AuthorDoesNotExistException;
import com.github.dimagithahahab.booklib.model.author.Author;
import com.github.dimagithahahab.booklib.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;

    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthor(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new AuthorDoesNotExistException("Author with such ID does not exist."));
    }

    public Author addAuthor(Author author) {
        authorRepository.save(author);

        return author;
    }


    public void delete(Long id) {
        if (!authorRepository.existsById(id)) {
            throw new AuthorDoesNotExistException("Author with such ID does not exist.");
        }

        authorRepository.deleteById(id);
    }


    public Author alterAuthor(Author author) {
        if (!authorRepository.existsById(author.getId())) {
            throw new AuthorDoesNotExistException("Author with such ID does not exist.");
        }

        authorRepository.save(author);

        return author;
    }


    public Author updateAuthor(Long id, Map<String, Object> updates) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new AuthorDoesNotExistException("Author with such ID does not exist."));

        updates.forEach((key, value) -> {
            switch (key) {
                case "firstName":
                    author.setFirstName((String) value);
                    break;
                case "lastName":
                    author.setLastName((String) value);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid field to modify: " + key);
            }
        });

        authorRepository.save(author);

        return author;
    }
}
