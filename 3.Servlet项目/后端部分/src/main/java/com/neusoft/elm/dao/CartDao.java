package com.neusoft.elm.dao;
import com.neusoft.elm.po.Cart;

import java.util.List;

public interface CartDao {
    public int saveCart(Cart cart) throws Exception;
    public int updateCart(Cart cart) throws Exception;
    public int removeCart(Cart cart) throws Exception;
    public List<Cart> listCart(Cart cart) throws Exception;

}
