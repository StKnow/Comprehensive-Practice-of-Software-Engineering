package com.neusoft.elm.service.impl;

import com.neusoft.elm.dao.CartDao;
import com.neusoft.elm.dao.impl.CartDaoImpl;
import com.neusoft.elm.po.Cart;
import com.neusoft.elm.service.CartService;
import com.neusoft.elm.util.DBUtil;

import java.util.ArrayList;
import java.util.List;

public class CartServiceImpl implements CartService {
    public int saveCart(Cart cart) {
        int result = 0;
        CartDao dao = new CartDaoImpl();
        try {
            DBUtil.getConnection();
            result = dao.saveCart(cart);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtil.close();
        }
        return result;
    }

    public int updateCart(Cart cart){
        int result = 0;
        CartDao dao = new CartDaoImpl();
        try {
            DBUtil.getConnection();
            result = dao.updateCart(cart);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtil.close();
        }
        return result;
    }

    public int removeCart(Cart cart) {
        int result = 0;
        CartDao dao = new CartDaoImpl();
        try {
            DBUtil.getConnection();
            result = dao.removeCart(cart);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtil.close();
        }
        return result;
    }

    public List<Cart> listCart(Cart cart){
        List<Cart> list = new ArrayList();
        CartDao dao = new CartDaoImpl();
        try {
            DBUtil.getConnection();
            list = dao.listCart(cart);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtil.close();
        }
        return list;
    }

}
