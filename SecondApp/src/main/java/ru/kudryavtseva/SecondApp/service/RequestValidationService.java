package ru.kudryavtseva.SecondApp.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import ru.kudryavtseva.SecondApp.exception.ValidationFailedException;
import ru.kudryavtseva.SecondApp.exception.UnsupportedCodeException;

@Service
public class RequestValidationService implements ValidationService {

    @Override
    public void isValid(BindingResult bindingResult) throws UnsupportedCodeException, ValidationFailedException  {

        if (bindingResult.hasErrors()) {

            for(FieldError error : bindingResult.getFieldErrors()){
                if(error.getField().equals("123")){
                    throw new UnsupportedCodeException(bindingResult.getFieldError().toString());
                } else {
                    throw new ValidationFailedException(bindingResult.getFieldError().toString());
                }
            }


        }

    }
}
