package com.neusoft.elm.dao.impl;

import com.neusoft.elm.dao.UserDao;
import com.neusoft.elm.po.Business;
import com.neusoft.elm.po.User;
import com.neusoft.elm.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDaoImpl implements UserDao {
    private Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    @Override
    public User getUserByPass(String userId, String password) throws Exception{
        User user = null;
        String sql = "select * from user where userId=? and password=?";
        try {
            con = DBUtil.getConnection();
            pst = con.prepareStatement(sql);
            pst.setString(1, userId);
            pst.setString(2,password);
            rs = pst.executeQuery();
            while(rs.next()) {
                user = new User();
                user.setUserId(rs.getString("userId"));
                user.setPassword(rs.getString("password"));
                user.setUserName(rs.getString("userName"));
                user.setUserSex(rs.getInt("userSex"));
                user.setUserImg(rs.getString("userImg"));
                user.setDelTag(rs.getInt("delTag"));
            }
        } finally {
            DBUtil.close(rs,pst);
        }
        return user;
    }
}
