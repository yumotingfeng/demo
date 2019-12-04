package com.example.demo.dto;

import lombok.Data;

@Data
public class Result<T> {
    private Integer code;
    private String message;
    private T data;
    private Integer total = 0;

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(1000);
        result.setMessage("success");
        result.setData(data);
        return result;
    }

    public static <T> Result<T> success(T data, Integer total) {
        Result<T> result = Result.success(data);
        result.setTotal(total);
        return result;
    }

    public static <T> Result<T> fail(Integer code, String message) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        result.setData(null);
        return result;
    }
}
