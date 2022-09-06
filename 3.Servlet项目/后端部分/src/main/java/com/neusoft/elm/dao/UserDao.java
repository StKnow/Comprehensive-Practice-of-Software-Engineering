package com.neusoft.elm.dao;

import com.neusoft.elm.po.User;

public interface UserDao {
    public User getUserByPass(String userId,String password) throws Exception;
}
