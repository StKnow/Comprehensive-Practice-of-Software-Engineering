package com.neusoft.elm.controller;

import com.neusoft.elm.po.User;
import com.neusoft.elm.service.UserService;
import com.neusoft.elm.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class UserController {
    public Object getUserByPass(HttpServletRequest request) throws Exception {
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");
        UserService service = new UserServiceImpl();
        User user = service.getUserByPass(userId, password);
        return user;
    }
}
