package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.exception.MyException;

import java.util.List;

public interface UserService {
    public List<User> getUsers(UserDto userDto) throws MyException;

    public Integer userCounts() throws MyException;

    public Integer addUser(User user) throws MyException;

    public Integer updateUser(User user) throws MyException;

    public Integer deleteUser(Integer id) throws MyException;
}
