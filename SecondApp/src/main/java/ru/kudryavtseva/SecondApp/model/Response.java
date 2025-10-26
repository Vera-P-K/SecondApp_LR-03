package ru.kudryavtseva.SecondApp.model;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response {

    private String uid;
    private String operationUid;
    private Codes code;
    private String systemTime;
    private ErrorCodes errorCode;
    private ErrorMessages errorMessage;

}
