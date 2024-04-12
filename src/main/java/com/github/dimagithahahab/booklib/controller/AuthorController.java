package com.github.dimagithahahab.booklib.controller;

import com.github.dimagithahahab.booklib.dto.AuthorDTO;
import com.github.dimagithahahab.booklib.model.author.Author;
import com.github.dimagithahahab.booklib.service.author.AuthorService;
import com.github.dimagithahahab.booklib.util.DTOConverter;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.github.dimagithahahab.booklib.util.DTOConverter.convertToEntity;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/lib/authors")
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping
    @Operation(summary = "List all the authors", tags = {"author"})
    @ResponseStatus(HttpStatus.OK)
    public List<AuthorDTO> getAllAuthors() {
        List<Author> authors = authorService.getAuthors();
        return authors.stream().map(DTOConverter::convertToDTO).collect(Collectors.toList());
    }

    @GetMapping(path = "{id}")
    @Operation(summary = "Get an author by its id", tags = {"author"})
    @ResponseStatus(HttpStatus.OK)
    public AuthorDTO getAuthor(@PathVariable("id") Long id) {
        Author author = authorService.getAuthor(id);
        return DTOConverter.convertToDTO(author);
    }

    @PostMapping
    @Operation(summary = "Add a new author", tags = {"author"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AuthorDTO> addActor(@RequestBody AuthorDTO authorDTO) {
        Author author = convertToEntity(authorDTO);

        Author savedAuthor = authorService.addAuthor(author);
        AuthorDTO savedAuthorDTO = DTOConverter.convertToDTO(savedAuthor);

        return ResponseEntity.status(HttpStatus.OK).body(savedAuthorDTO);
    }

    @DeleteMapping(path = "{id}")
    @Operation(summary = "Delete an author by its id", tags = {"author"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> deleteActor(@PathVariable Long id) {
        authorService.delete(id);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("{id}")
    @Operation(summary = "Update an author by its id", tags = {"author"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AuthorDTO> updateBook(@PathVariable("id") Long id, @RequestBody AuthorDTO authorDTO) {
        Author author = convertToEntity(authorDTO);
        author.setId(id);

        Author savedAuthor = authorService.alterAuthor(author);
        AuthorDTO savedAuthorDTO = DTOConverter.convertToDTO(savedAuthor);

        return ResponseEntity.status(HttpStatus.OK).body(savedAuthorDTO);
    }

    @PatchMapping("{id}")
    @Operation(summary = "Update an author by its id", tags = {"author"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AuthorDTO> patchBook(@PathVariable("id") Long id, @RequestBody Map<String, Object> updates) {
        Author savedAuthor = authorService.updateAuthor(id, updates);
        AuthorDTO savedAuthorDTO = DTOConverter.convertToDTO(savedAuthor);

        return ResponseEntity.status(HttpStatus.OK).body(savedAuthorDTO);
    }


}
