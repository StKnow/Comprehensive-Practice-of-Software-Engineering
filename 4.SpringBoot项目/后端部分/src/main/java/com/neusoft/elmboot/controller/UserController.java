package com.neusoft.elmboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.neusoft.elmboot.po.User;
import com.neusoft.elmboot.service.UserService;

@RestController
@RequestMapping("/UserController")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/getUserByIdByPass")
    public User getUserByIdByPass(User user) throws Exception {
        return userService.getUserByIdByPass(user);
    }

}
