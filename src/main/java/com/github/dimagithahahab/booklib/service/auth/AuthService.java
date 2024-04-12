package com.github.dimagithahahab.booklib.service.auth;

import com.github.dimagithahahab.booklib.exception.InvalidLoginException;
import com.github.dimagithahahab.booklib.exception.UserAlreadyExistsException;
import com.github.dimagithahahab.booklib.model.user.User;
import com.github.dimagithahahab.booklib.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtManage jwtService;

    private static void validateEmailAndPassword(User user) {
        if (user == null || user.getEmail() == null || user.getPassword() == null) {
            throw new InvalidLoginException("Invalid email or password");
        }
    }

    public void register(User newUser) {
        validateEmailAndPassword(newUser);
        if (newUser.getName() == null) {
            throw new InvalidLoginException("Name is required");
        }

        if (repository.existsByEmail(newUser.getEmail())) {
            throw new UserAlreadyExistsException("Email already in use.");
        }

        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

        repository.save(newUser);

    }

    public String login(User loggingUser) {
        validateEmailAndPassword(loggingUser);

        Optional<User> existingUser = repository.findByEmail(loggingUser.getEmail());
        if (existingUser.isPresent() && passwordEncoder.matches(loggingUser.getPassword(), existingUser.get().getPassword())) {
            return jwtService.generateToken(existingUser.get());
        }

        throw new InvalidLoginException("Invalid email or password");
    }
}

