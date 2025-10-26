package ru.kudryavtseva.SecondApp.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import ru.kudryavtseva.SecondApp.exception.ValidationFailedException;
import ru.kudryavtseva.SecondApp.exception.UnsupportedCodeException;

@Service
public class RequestValidationService implements ValidationService {

    @Override
    public void isValid(BindingResult bindingResult) throws  ValidationFailedException  {
if (bindingResult.hasErrors()){
                    throw new ValidationFailedException(bindingResult.getFieldError().toString());
               }
            }


        }


