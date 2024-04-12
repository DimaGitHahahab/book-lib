package com.github.dimagithahahab.booklib.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthorDoesNotExistException.class)
    public ResponseEntity<String> handleEmailAlreadyExistsException(AuthorDoesNotExistException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookAlreadyExistsException.class)
    public ResponseEntity<String> handleStudentNotFoundException(BookAlreadyExistsException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BookDoesNotExistException.class)
    public ResponseEntity<String> handleBookDoesNotExistException(BookDoesNotExistException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return new ResponseEntity<>("Invalid JSON provided", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<String> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return new ResponseEntity<>("The requested method is not supported.", HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(InvalidLoginException.class)
    public ResponseEntity<String> handleInvalidLoginException(InvalidLoginException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> handleUserAlreadyExistsException(UserAlreadyExistsException e) {
        return new ResponseEntity<>("Email is already taken.", HttpStatus.CONFLICT);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>("Internal error.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

