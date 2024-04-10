package com.github.dimagithahahab.booklib.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class AuthorDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private Set<SimplifiedBookDTO> books;

    @Getter
    @Setter
    @ToString
    @RequiredArgsConstructor
    @AllArgsConstructor
    public static class SimplifiedBookDTO {
        private Long id;
        private String title;
    }
}