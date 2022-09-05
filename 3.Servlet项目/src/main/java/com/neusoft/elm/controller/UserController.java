package com.neusoft.elm.controller;

import com.neusoft.elm.po.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserController {
    public Object login(HttpServletRequest request) {
        String userName = request.getParameter("username");
        System.out.println(userName);
        User user = new User();
        user.setUserId("123456");
        user.setUserName("zhangsan");
        return user;
    }
}
