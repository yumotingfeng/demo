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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
public class UserController {
    @Resource
    private UserService userService;

    private final String KEY = "code";

    private final String EXPIRED = "expired";

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
    public Result<Object> deleteUser(@NotNull @RequestBody User user) throws MyException {
        userService.deleteUser(user.getId());
        return Result.success(null);
    }

    @PostMapping("/upload")
    public Result<FileDto> upload(@NotNull @RequestParam("file") MultipartFile multipartFile) throws MyException {
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
            boolean b = file.mkdirs();
            if (!b) {
                throw new MyException(1005, "文件夹创建失败");
            }
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

    @GetMapping("/captcha")
    public Result<Integer> captcha(@NotNull HttpServletRequest request) {
        int code = (int) ((Math.random() * 9 + 1) * 100000);
        HttpSession session = request.getSession();
        int time = (int) (new Date().getTime() / 1000);
        session.setAttribute(KEY, code);
        session.setAttribute(EXPIRED, time);
        return Result.success(code);
    }

    @GetMapping("checkCaptcha")
    public Result<Integer> checkCaptcha(@NotNull HttpServletRequest request) throws MyException {
        HttpSession session = request.getSession();
        int code = (int) session.getAttribute(KEY);
        int now = (int) (new Date().getTime() / 1000);
        int time = (int) session.getAttribute(EXPIRED);

        if (now - time >= 60) {
            throw new MyException(2000, "验证码过期");
        }
        String inputCode = request.getParameter("code");

        if (inputCode == null) {
            throw new MyException(2000, "验证码不能为空");
        }

        int strCode = Integer.parseInt(inputCode);

        if (strCode != code) {
            throw new MyException(2000, "验证码不匹配");
        }

        return Result.success(code);
    }

    public static void main(String[] args) {

    }
}
