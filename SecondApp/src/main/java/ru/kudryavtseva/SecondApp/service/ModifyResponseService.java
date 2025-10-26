package ru.kudryavtseva.SecondApp.service;

import org.springframework.stereotype.Service;
import ru.kudryavtseva.SecondApp.model.Response;

@Service
public interface ModifyResponseService {

    Response modify (Response response);
}
