package com.neusoft.elm.service;

import com.neusoft.elm.po.User;

public interface UserService  {
    public User getUserByPass(String userId, String password);
    public int getUserById(String userId);
    public int saveUser(User user);
}
