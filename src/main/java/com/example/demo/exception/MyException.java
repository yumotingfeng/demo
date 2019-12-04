package com.example.demo.exception;

public class MyException extends Exception {
    private Integer code = 0;
    private String message;

    public MyException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
