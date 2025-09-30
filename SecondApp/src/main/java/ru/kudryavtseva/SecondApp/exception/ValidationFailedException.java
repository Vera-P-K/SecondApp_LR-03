package ru.kudryavtseva.SecondApp.exception;

public class ValidationFailedException extends Exception {
    public ValidationFailedException(String message) {
        super(message);
    }
}
