package com.github.dimagithahahab.booklib.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private Long id;
    private String title;
    private String isbn;
    private LocalDate publishedDate;
    private AuthorDTO author;

    @Getter
    @Setter
    @ToString
    @RequiredArgsConstructor
    @AllArgsConstructor
    public static class AuthorDTO {
        private Long id;
        private String name;
    }
}