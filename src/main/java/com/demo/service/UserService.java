package com.demo.service;

import com.demo.dto.UserDto;
import com.demo.domain.User;
import com.demo.exception.MyException;

import java.util.List;

public interface UserService {
    public List<User> getUsers(UserDto userDto) throws MyException;

    public Integer userCounts() throws MyException;

    public Integer addUser(User user) throws MyException;

    public Integer updateUser(User user) throws MyException;

    public Integer deleteUser(Integer id) throws MyException;
}
