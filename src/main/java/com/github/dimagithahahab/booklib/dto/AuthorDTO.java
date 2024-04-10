package com.github.dimagithahahab.booklib.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@ToString
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class AuthorDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private Set<BookDTO> books;

    @Getter
    @Setter
    @ToString
    @Builder
    @RequiredArgsConstructor
    @AllArgsConstructor
    public static class BookDTO {
        private Long id;
        private String title;
    }
}