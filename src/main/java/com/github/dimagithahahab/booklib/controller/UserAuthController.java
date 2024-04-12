package com.github.dimagithahahab.booklib.controller;

import com.github.dimagithahahab.booklib.dto.UserDTO;
import com.github.dimagithahahab.booklib.model.user.User;
import com.github.dimagithahahab.booklib.service.auth.AuthService;
import com.github.dimagithahahab.booklib.util.DTOConverter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class UserAuthController {
    private final AuthService authService;

    private static void addTokenToCookie(HttpServletResponse response, String token) {
        Cookie jwtCookie = new Cookie("access_token", token);
        jwtCookie.setHttpOnly(true);
        jwtCookie.setSecure(true);
        jwtCookie.setPath("/");
        response.addCookie(jwtCookie);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody UserDTO newUserDTO) {
        User newUser = DTOConverter.convertToEntity(newUserDTO);

        authService.register(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserDTO loggingUserDTO, HttpServletResponse response) {
        User loggingUser = DTOConverter.convertToEntity(loggingUserDTO);

        String token = authService.login(loggingUser);

        addTokenToCookie(response, token);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

}

