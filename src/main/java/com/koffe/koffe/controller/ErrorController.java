package com.koffe.koffe.controller;

import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handleError404(Exception ex) {
        System.out.println(ex.toString());
        return "/user/pages/404";
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public String handleError405(Exception ex) {
        System.out.println(ex.toString());
        return "/user/pages/404";
    }

}