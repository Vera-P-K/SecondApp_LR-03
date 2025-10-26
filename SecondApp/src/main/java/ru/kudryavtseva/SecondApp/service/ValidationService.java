package ru.kudryavtseva.SecondApp.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import ru.kudryavtseva.SecondApp.exception.ValidationFailedException;
import ru.kudryavtseva.SecondApp.exception.UnsupportedCodeException;


@Service
public interface ValidationService {

    void isValid(BindingResult bindingResult) throws UnsupportedCodeException,
            ValidationFailedException;

}
