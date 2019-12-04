package com.example.demo.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class UserDto {
    @NotNull(message = "page当前页码不能为空")
    @Min(value = 1, message = "当前页码最小为1")
    private Integer page;
    @Min(value = 1, message = "当前分页数最小为1")
    @NotNull(message = "pageSize当前分页数不能为空")
    private Integer pageSize;

    public Integer getPage() {
        return (page - 1) * pageSize;
    }
}
