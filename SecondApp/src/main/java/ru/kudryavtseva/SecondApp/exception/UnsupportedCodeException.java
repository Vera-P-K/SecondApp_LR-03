package ru.kudryavtseva.SecondApp.exception;


public class UnsupportedCodeException extends ValidationFailedException {
    public UnsupportedCodeException(String message) {
        super(message);
    }
}
