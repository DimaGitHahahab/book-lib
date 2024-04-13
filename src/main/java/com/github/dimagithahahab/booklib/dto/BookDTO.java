package com.github.dimagithahahab.booklib.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private Long id;

    @NotBlank(message = "Title is mandatory")
    private String title;

    @NotBlank(message = "ISBN is mandatory")
    private String isbn;

    @NotNull(message = "Published date is mandatory")
    private LocalDate publishedDate;

    @NotNull(message = "Author is mandatory")
    private AuthorDTO author;

    @Getter
    @Setter
    @ToString
    @Builder
    @RequiredArgsConstructor
    @AllArgsConstructor
    public static class AuthorDTO {
        private Long id;
        private String name;
    }
}