package com.neusoft.elmboot.service.impl;

import com.neusoft.elmboot.mapper.UserMapper;
import com.neusoft.elmboot.po.User;
import com.neusoft.elmboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByIdByPass(User user) {
        return userMapper.getUserByIdByPass(user);
    }
}
