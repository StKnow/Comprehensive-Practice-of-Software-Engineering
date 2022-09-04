package com.neusoft.elm.view.impl;


import java.util.List;
import java.util.Scanner;
import com.neusoft.elm.dao.BusinessDao;
import com.neusoft.elm.dao.imply.BusinessDaoImpl;
import com.neusoft.elm.po.Business;
import com.neusoft.elm.view.BusinessView;

public class BusinessViewImpl implements BusinessView {
    private Scanner input = new Scanner(System.in);
    @Override
    public void listBusinessAll() {
        BusinessDao dao = new BusinessDaoImpl();
        List<Business> list = dao.listBusiness(null,null);
        System.out.println("商家编号\t商家名称\t商家地址\t商家介绍\t起送费\t配送费");
        for (Business business : list) {
            System.out.println(business.getBusinessId() + "\t" + business.getBusinessName() + "\t" + business.getBusinessAddress() + "\t" + business.getBusinessExplain() + "\t" + business.getStartPrice() + "\t" + business.getDeliveryPrice());
        }
    }

    @Override
    public void listBusiness() {
        String businessName = "";
        String businessAddress = "";

        String inputStr = "";
        System.out.println("是否需要输入商家名称关键词(y/n)：");
        inputStr = input.next();
        if(inputStr.equals("y")) {
            System.out.println("请输入商家名称关键词：");
            businessName = input.next();
        }

        System.out.println("是否需要输入商家地址关键词(y/n)：");
        inputStr = input.next();
        if(inputStr.equals("y")) {
            System.out.println("请输入商家地址关键词：");
            businessAddress = input.next();
        }

        BusinessDao dao = new BusinessDaoImpl();
        List<Business> list = dao.listBusiness(businessName, businessAddress);
        System.out.println("商家编号\t商家名称\t商家地址\t商家介绍\t起送费\t配送费");
        for (Business business : list) {
            System.out.println(business.getBusinessId() + "\t" + business.getBusinessName() + "\t" + business.getBusinessAddress() + "\t" + business.getBusinessExplain() + "\t" + business.getStartPrice() + "\t" + business.getDeliveryPrice());
        }

    }

    @Override
    public void saveBusiness() {
        System.out.println("请输入商家名称：");
        String businessName = input.next();
        BusinessDao dao = new BusinessDaoImpl();
        int businessId = dao.saveBusiness(businessName);
        if(businessId>0) {
            System.out.println("新建商家成功！商家编号为："+businessId);
        }else {
            System.out.println("新建商家失败！");
        }
    }

    @Override
    public void removeBusiness() {
        System.out.println("请输入商家编号：");
        int businessId = input.nextInt();

        BusinessDao dao = new BusinessDaoImpl();
        System.out.println("确认要删除吗(y/n)：");
        if(input.next().equals("y")) {
            int result = dao.removeBusiness(businessId);
            if(result==1) {
                System.out.println("删除商家成功！");
            }else {
                System.out.println("删除商家失败！");
            }
        }
    }

    @Override
    public Business login() {
        System.out.println("请输入商家编号：");
        int businessId = input.nextInt();
        System.out.println("请输入密码：");
        String password = input.next();

        BusinessDao dao = new BusinessDaoImpl();
        return dao.getBusinessByIdByPass(businessId, password);
    }

    @Override
    public void showBusiness(Integer businessId) {
        BusinessDao dao = new BusinessDaoImpl();
        Business business = dao.getBusinessById(businessId);
        System.out.println(business);
    }

    @Override
    public void editBusiness(Integer businessId) {
        //先将商家信息查询出来显示，然后用户才能更新
        BusinessDao dao = new BusinessDaoImpl();
        Business business = dao.getBusinessById(businessId);
        System.out.println(business);

        String inputStr = "";
        System.out.println("是否修改商家名称(y/n)：");
        inputStr = input.next();
        if(inputStr.equals("y")) {
            System.out.println("请输入新的商家名称：");
            business.setBusinessName(input.next());
        }

        System.out.println("是否修改商家地址(y/n)：");
        inputStr = input.next();
        if(inputStr.equals("y")) {
            System.out.println("请输入新的商家地址：");
            business.setBusinessAddress(input.next());
        }

        System.out.println("是否修改商家介绍(y/n)：");
        inputStr = input.next();
        if(inputStr.equals("y")) {
            System.out.println("请输入新的商家介绍：");
            business.setBusinessExplain(input.next());
        }

        System.out.println("是否修改起送费(y/n)：");
        inputStr = input.next();
        if(inputStr.equals("y")) {
            System.out.println("请输入新的起送费：");
            business.setStartPrice(input.nextDouble());
        }

        System.out.println("是否修改配送费(y/n)：");
        inputStr = input.next();
        if(inputStr.equals("y")) {
            System.out.println("请输入新的配送费：");
            business.setDeliveryPrice(input.nextDouble());
        }

        int result = dao.updateBusiness(business);
        if(result>0) {
            System.out.println("\n修改商家信息成功！\n");
        }else {
            System.out.println("\n修改商家信息失败！\n");
        }
    }

    @Override
    public void updateBusinessByPassword(Integer businessId) {
        BusinessDao dao = new BusinessDaoImpl();
        Business business = dao.getBusinessById(businessId);

        System.out.println("\n请输入旧密码：");
        String oldPass = input.next();
        System.out.println("\n请输入新密码：");
        String password = input.next();
        System.out.println("\n请再次输入新密码：");
        String beginPassword = input.next();

        if(!business.getPassword().equals(oldPass)) {
            System.out.println("\n旧密码输入错误！");
        }else if(!password.equals(beginPassword)) {
            System.out.println("\n两次输入密码不一致！");
        }else {
            int result = dao.updateBusinessByPassword(businessId, password);
            if(result>0) {
                System.out.println("\n修改密码成功！");
            }else {
                System.out.println("\n修改密码失败！");
            }
        }
    }

}
