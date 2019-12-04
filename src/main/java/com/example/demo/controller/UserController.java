package com.example.demo.controller;

import com.example.demo.dto.Result;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.exception.MyException;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("/getUsers")
    public Result<List<User>> getUsers(@Valid UserDto userDto) throws MyException {
        List<User> users = userService.getUsers(userDto);
        Integer total = userService.userCounts();
        return Result.success(users, total);
    }

    @PostMapping("/addUser")
    public Result<Object> addUser(@Valid @RequestBody User user) throws MyException {
        userService.addUser(user);
        return Result.success(null);
    }

    @PostMapping("/updateUser")
    public Result<Object> updateUser(@RequestBody User user) throws MyException {
        userService.updateUser(user);
        return Result.success(null);
    }

    @PostMapping("/deleteUser")
    public Result<Object> deleteUser(@RequestBody User user) throws MyException {
        userService.deleteUser(user.getId());
        return Result.success(null);
    }

    @GetMapping("/testException")
    public void testException() throws MyException {
        throw new MyException(1002, "test1");
    }
}