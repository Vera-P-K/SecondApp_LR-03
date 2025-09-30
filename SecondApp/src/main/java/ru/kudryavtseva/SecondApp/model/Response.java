package ru.kudryavtseva.SecondApp.model;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response {

    private String uid;
    private String operationUid;
    private String code;
    private String systemTime;
    private String errorCode;
    private String errorMessage;

}
