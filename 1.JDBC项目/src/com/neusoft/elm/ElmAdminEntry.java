package com.neusoft.elm;

import com.mysql.cj.util.StringUtils;
import com.neusoft.elm.po.Admin;
import com.neusoft.elm.po.Business;
import com.neusoft.elm.view.AdminView;
import com.neusoft.elm.view.BusinessView;
import com.neusoft.elm.view.impl.AdminViewImpl;
import com.neusoft.elm.view.impl.BusinessViewImpl;

import java.util.Scanner;

public class ElmAdminEntry {
    public static void main(String[] args) {
        ElmAdminEntry elmAdminEntry = new ElmAdminEntry();
        elmAdminEntry.work();
    }

    public void work() {
        Scanner input = new Scanner(System.in);
        System.out.println("---------------------------------------------");
        System.out.println("\t\t\t 饿了么后台管理系统 \t\t\t");
        System.out.println("---------------------------------------------");

        AdminView adminView = new AdminViewImpl();
        Admin admin = adminView.login();
        BusinessView businessView = new BusinessViewImpl();
        //登录
        if (admin!=null) {
            String tmp = "";
            int menu = 0;
            while (menu != 5) {
                System.out.println("\n---------- 1.所有商家列表 2.搜索商家 3.新建商家 4.删除商家 5.退出系统----------");
                System.out.println("请输入你的选择");
                tmp = input.next();
                if(!(StringUtils.isStrictlyNumeric(tmp))) {
                    System.out.println("请输入正确格式的数字选项！");
                } else {
                    menu = Integer.valueOf(tmp);
                    switch (menu) {
                        case 1:
                            businessView.listBusinessAll();
                            break;
                        case 2:
                            businessView.listBusiness();
                            break;
                        case 3:
                            businessView.saveBusiness();
                            break;
                        case 4:
                            businessView.removeBusiness();
                            break;
                        case 5:
                            System.out.println("----------欢迎下次光临饿了么后台管理系统----------");
                            break;
                        default:
                            System.out.println("\n没有这个选项");
                    }
                }

            }
        } else {
            System.out.println("\n管理员名称或密码错误!\n");
        }
    }


}
