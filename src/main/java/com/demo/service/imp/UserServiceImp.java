package com.demo.service.imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.entity.User;
import com.demo.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 * @author daijh
 */
@Service
public class UserServiceImp extends ServiceImpl<UserMapper, User> {}
