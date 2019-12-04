package com.example.demo.dao;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.exception.MyException;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {
    public List<User> getUsers(UserDto userDto) throws MyException;

    public Integer userCounts() throws MyException;

    public Integer addUser(User user) throws MyException;

    public Integer updateUser(User user) throws MyException;

    public Integer deleteUser(Integer id) throws MyException;
}
