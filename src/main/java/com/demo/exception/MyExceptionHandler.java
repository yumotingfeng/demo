package com.demo.exception;

import com.demo.req.Result;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result<?> ExceptionHandler(Exception exception) {
        int code = 0;
        String message = "";
        if (exception instanceof MyException) {
            MyException e = (MyException) exception;
            code = e.getCode();
            message = e.getMessage();
        } else if (exception instanceof BindException) {
            BindException e = (BindException) exception;
            code = 1001;
            message = e.getBindingResult().getFieldError().getDefaultMessage();
        } else if(exception instanceof HttpMessageNotReadableException){
            HttpMessageNotReadableException e = (HttpMessageNotReadableException) exception;
            code = 1002;
            message = e.getMessage();
        }
        return Result.fail(code, message);
    }
}
