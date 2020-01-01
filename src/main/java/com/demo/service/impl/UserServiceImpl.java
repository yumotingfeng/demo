package com.demo.service.impl;

import com.demo.service.UserService;
import com.demo.mapper.UserDao;
import com.demo.dto.UserDto;
import com.demo.domain.User;
import com.demo.exception.MyException;
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
