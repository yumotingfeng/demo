package com.demo.req;

import lombok.Data;

@Data
public class Result<T> {
    private Integer code;
    private String message;
    private T data;
    private Long total = 0L;

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(1000);
        result.setMessage("success");
        result.setData(data);
        return result;
    }

    public static <T> Result<T> success(T data, Long total) {
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
