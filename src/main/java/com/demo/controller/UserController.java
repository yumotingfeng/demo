package com.demo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.entity.User;
import com.demo.req.Result;
import com.demo.req.UserReq;
import com.demo.service.imp.UserServiceImp;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author daijh
 */
@RestController
public class UserController {

    @Resource
    private UserServiceImp userServiceImp;

    @GetMapping("/getUsers")
    public Result<List<User>> getUsers(UserReq userReq) {
        Page<User> page = new Page<>(1, 3);
        Page<User> userPage = userServiceImp.page(page);
        return Result.success(userPage.getRecords(), userPage.getTotal());
    }

    @GetMapping("/test1")
    public RedirectView test1() {
        RedirectView redirectTarget = new RedirectView();
        redirectTarget.setContextRelative(true);
        redirectTarget.setUrl("test2");
        return redirectTarget;
    }

    @GetMapping("/test2")
    public RedirectView test2() {
        RedirectView redirectTarget = new RedirectView();
        redirectTarget.setContextRelative(true);
        redirectTarget.setUrl("test3");
        return redirectTarget;
    }

    @GetMapping("/test3")
    public RedirectView test3() {
        RedirectView redirectTarget = new RedirectView();
        redirectTarget.setContextRelative(true);
        redirectTarget.setUrl("test4");
        return redirectTarget;
    }

    @GetMapping("/test4")
    public RedirectView test4() {
        RedirectView redirectTarget = new RedirectView();
        redirectTarget.setContextRelative(true);
        redirectTarget.setUrl("test5");
        return redirectTarget;
    }

    @GetMapping("/test5")
    public String test5() {
        return "success this is a test5";
    }

    public static void main(String[] args) {

    }
}
