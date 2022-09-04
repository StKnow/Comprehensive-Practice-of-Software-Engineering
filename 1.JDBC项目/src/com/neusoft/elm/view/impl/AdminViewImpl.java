package com.neusoft.elm.view.impl;

import com.neusoft.elm.dao.AdminDao;
import com.neusoft.elm.dao.imply.AdminDaoImpl;
import com.neusoft.elm.po.Admin;
import com.neusoft.elm.view.AdminView;

import java.util.Scanner;

public class AdminViewImpl implements AdminView {
    private Scanner input = new Scanner(System.in);

    @Override
    public Admin login() {
        System.out.println("请输入管理员名称: ");
        String adminName = input.next();
        System.out.println("请输入密码: ");
        String password = input.next();
        AdminDao dao = new AdminDaoImpl();
        Admin admin = dao.getAdminByNameByPass(adminName, password);
        return admin;
    }
}
