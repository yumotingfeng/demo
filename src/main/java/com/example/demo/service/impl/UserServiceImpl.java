package com.example.demo.service.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.exception.MyException;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public List<User> getUsers(UserDto userDto) throws MyException {
        return userDao.getUsers(userDto);
    }

    @Override
    public Integer userCounts() throws MyException {
        return userDao.userCounts();
    }

    @Override
    public Integer addUser(User user) throws MyException {
        return userDao.addUser(user);
    }

    @Override
    public Integer updateUser(User user) throws MyException {
        return userDao.updateUser(user);
    }

    @Override
    public Integer deleteUser(Integer id) throws MyException {
        return userDao.deleteUser(id);
    }
}
