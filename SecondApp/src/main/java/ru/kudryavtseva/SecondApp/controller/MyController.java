package ru.kudryavtseva.SecondApp.controller;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.kudryavtseva.SecondApp.exception.UnsupportedCodeException;
import ru.kudryavtseva.SecondApp.exception.ValidationFailedException;
import ru.kudryavtseva.SecondApp.model.*;
import ru.kudryavtseva.SecondApp.service.ModifyResponseService;
import ru.kudryavtseva.SecondApp.service.ModifySystemTimeResponseService;
import ru.kudryavtseva.SecondApp.service.RequestUnsupportedCodeException;
import ru.kudryavtseva.SecondApp.service.ValidationService;
import ru.kudryavtseva.SecondApp.util.DateTimeUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@RestController
public class MyController {

    private final ValidationService validationService;

    private final ModifyResponseService modifyResponseService;

    private final RequestUnsupportedCodeException requestUnsupportedCodeException;

@Autowired
public MyController(@Qualifier("RequestUnsupportedCodeException")
                    RequestUnsupportedCodeException
                            requestUnsupportedCodeException,
                    ValidationService validationService,
                    @Qualifier("ModifySystemTimeResponseService")
                    ModifyResponseService modifyResponseService)
{
    this.requestUnsupportedCodeException=requestUnsupportedCodeException;
    this.validationService = validationService;
    this.modifyResponseService = modifyResponseService;
}

    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback (@Valid @RequestBody Request request,
                                              BindingResult bindingResult) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

        log.info("Request: {}", request);

        Response response = Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(DateTimeUtil.getCustomFormat().format(new Date()))
                .code(Codes.SUCCESS)
                .errorCode(ErrorCodes.EMPTY)
                .errorMessage(ErrorMessages.EMPTY)
                .build();
log.info("Response: {}", response);
        try {
            validationService.isValid(bindingResult);

            for(FieldError error : bindingResult.getFieldErrors()){
                if(error.getField().equals("123")){
                    throw new UnsupportedCodeException(bindingResult.getFieldError().toString());
                } else {
                    throw new ValidationFailedException(bindingResult.getFieldError().toString());
                }
            }
        } catch (UnsupportedCodeException e){
            log.error("response: {}", response);
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.UNSUPPORTED_EXCEPTION);
            response.setErrorMessage(ErrorMessages.UNSUPPORTED);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

                } catch (ValidationFailedException e) {
            log.error("response: {}", response);
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.VALIDATION_EXCEPTION);
            response.setErrorMessage(ErrorMessages.VALIDATION);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
                          } catch (Exception e) {
            log.error("response: {}", response);
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.UNKNOWN_EXCEPTION);
            response.setErrorMessage(ErrorMessages.UNKNOWN);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        modifyResponseService.modify(response);

        return new ResponseEntity<>(modifyResponseService.modify(response), HttpStatus.OK);
    }


}
