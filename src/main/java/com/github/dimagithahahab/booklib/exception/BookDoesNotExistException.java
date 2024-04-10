package com.github.dimagithahahab.booklib.exception;

public class BookDoesNotExistException extends RuntimeException{
    public BookDoesNotExistException(String message) {
        super(message);
    }
}
