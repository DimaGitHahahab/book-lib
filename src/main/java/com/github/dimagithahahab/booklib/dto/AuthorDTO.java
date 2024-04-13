package com.github.dimagithahahab.booklib.dto;

import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "First name is mandatory")
    private String firstName;

    @NotBlank(message = "First name is mandatory")
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