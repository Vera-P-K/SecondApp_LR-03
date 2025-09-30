package ru.kudryavtseva.SecondApp.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.kudryavtseva.SecondApp.exception.UnsupportedCodeException;
import ru.kudryavtseva.SecondApp.exception.ValidationFailedException;
import ru.kudryavtseva.SecondApp.model.Request;
import ru.kudryavtseva.SecondApp.model.Response;
import ru.kudryavtseva.SecondApp.service.ValidationService;
import ru.kudryavtseva.SecondApp.util.DateTimeUtil;

import java.text.SimpleDateFormat;
import java.util.Date;


@RestController
public class MyController {

    private final ValidationService validationService;

@Autowired
public MyController(ValidationService validationService){
this.validationService=validationService;
}

    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback (@Valid @RequestBody Request request,
                                              BindingResult bindingResult) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

        Response response = Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(DateTimeUtil.getCustomFormat().format(new Date()))
                .code("success")
                .errorCode("")
                .errorMessage("")
                .build();
        try {
            validationService.isValid(bindingResult);
        } catch (UnsupportedCodeException c){
            response.setCode("failed");
            response.setErrorCode("ValidationException");
            response.setErrorMessage("Ошибка валидации");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (ValidationFailedException e) {
            response.setCode("failed");
            response.setErrorCode("UnsupportedCodeException");
            response.setErrorMessage("Не поддерживаемая ошибка");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
           catch (Exception e) {
            response.setCode("failed");
            response.setErrorCode("UnknownException");
            response.setErrorMessage("Произошла непредвиденная ошибка");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
