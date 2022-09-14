package com.neusoft.elm.controller;

import com.neusoft.elm.po.User;
import com.neusoft.elm.service.UserService;
import com.neusoft.elm.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class UserController {
    public Object getUserByIdByPass(HttpServletRequest request) throws Exception {
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");
        UserService service = new UserServiceImpl();
        User user = service.getUserByPass(userId, password);
        return user;
    }

    public Object getUserById(HttpServletRequest request) throws Exception{
        String userId = request.getParameter("userId");
        UserService service = new UserServiceImpl();
        int result = service.getUserById(userId);
        return result;
    }

    public Object saveUser(HttpServletRequest request) throws Exception{
        User user = new User();
        user.setUserId(request.getParameter("userId"));
        user.setPassword(request.getParameter("password"));
        user.setUserName(request.getParameter("userName"));
        user.setUserSex(Integer.valueOf(request.getParameter("userSex")));
        UserService service = new UserServiceImpl();
        int result = service.saveUser(user);
        return result;
    }
}
