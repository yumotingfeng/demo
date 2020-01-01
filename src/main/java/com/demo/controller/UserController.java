package com.demo.controller;

import com.demo.dto.FileDto;
import com.demo.dto.Result;
import com.demo.dto.UserDto;
import com.demo.domain.User;
import com.demo.exception.MyException;
import com.demo.service.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

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

    @PostMapping("/upload")
    public Result<FileDto> upload(@RequestParam("file") MultipartFile multipartFile) throws MyException {
        if (multipartFile.isEmpty()) {
            throw new MyException(1003, "上传文件为空");
        }
//        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "static/";
        String path = "/usr/local/nginx/html/pic/";
        String originalFilename = multipartFile.getOriginalFilename();
        assert originalFilename != null;
        String ext = originalFilename.substring(originalFilename.lastIndexOf("."));
        String FileName = UUID.randomUUID() + ext;
        File file = new File(path + FileName);
        if (!file.isDirectory()) {
            file.mkdirs();
        }
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
            throw new MyException(1004, "文件保存失败");
        }
        FileDto fileDto = new FileDto();
        fileDto.setUrl(FileName);
        return Result.success(fileDto);
    }
}