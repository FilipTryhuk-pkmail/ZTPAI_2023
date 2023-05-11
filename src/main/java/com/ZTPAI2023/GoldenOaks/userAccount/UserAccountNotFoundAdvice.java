package com.ZTPAI2023.GoldenOaks.userAccount;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UserAccountNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(UserAccountNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String userAccountNotFoundException(UserAccountNotFoundException exception) {
        return exception.getMessage();
    }
}
