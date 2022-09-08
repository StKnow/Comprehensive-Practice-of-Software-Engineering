package com.neusoft.elm.service.impl;

import com.neusoft.elm.dao.CartDao;
import com.neusoft.elm.dao.OrderDetailetDao;
import com.neusoft.elm.dao.OrdersDao;
import com.neusoft.elm.dao.impl.CartDaoImpl;
import com.neusoft.elm.dao.impl.OrderDetailetDaoImpl;
import com.neusoft.elm.dao.impl.OrdersDaoImpl;
import com.neusoft.elm.po.Cart;
import com.neusoft.elm.po.OrderDetailet;
import com.neusoft.elm.po.Orders;
import com.neusoft.elm.service.OrdersService;
import com.neusoft.elm.util.DBUtil;

import java.util.ArrayList;
import java.util.List;

public class OrdersServiceImpl implements OrdersService {

    @Override
    public int createOrder(String userId, Integer businessId, Integer daId, Double orderTotal) {
        int orderId = 0;
        CartDao cartdao = new CartDaoImpl();
        OrdersDao orderDao = new OrdersDaoImpl();
        OrderDetailetDao orderDetailetDao = new OrderDetailetDaoImpl();
        try {
            DBUtil.beginTransaction();  //开启一个事务

            //1.查询当前用户购物车中当前商家的所有食品(放入订单明细)
            Cart cart = new Cart();
            cart.setUserId(userId);
            cart.setCartId(businessId);
            List<Cart> cartList = cartdao.listCart(cart);

            //2.创建订单，并获取订单编号
            Orders orders = new Orders();
            orders.setUserId(userId);
            orders.setBusinessId(businessId);
            orders.setDaId(daId);
            orders.setOrderTotal(orderTotal);
            orderId = orderDao.saveOrders(orders);

            //3.向订单明细表中批量插入数据
            List<OrderDetailet> orderDetailetList = new ArrayList<>();
            for (Cart c : cartList) {
                OrderDetailet orderDetailet = new OrderDetailet();
                orderDetailet.setOrderId(orderId);
                orderDetailet.setFoodId(c.getFoodId());
                orderDetailet.setQuantity(c.getQuantity());
                orderDetailetList.add(orderDetailet);
            }
            orderDetailetDao.saveOrderDetailetBatch(orderDetailetList);

            //4.清空购物车(条件：当前用户、当前商家)
            cartdao.removeCart(cart);

            DBUtil.commitTransaction(); //提交一个事物
        } catch (Exception e) {
            orderId = 0;
            try {
                DBUtil.rollbackTransaction();   //回滚一个事务
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            DBUtil.close();
        }
        return 0;
    }

    @Override
    public Orders getOrdersById(Integer orderId) {
        Orders orders = null;
        OrdersDao ordersDao = new OrdersDaoImpl();
        OrderDetailetDao orderDetailetDao = new OrderDetailetDaoImpl();

        try {
           DBUtil.getConnection();

           //1.根据订单id查询订单信息(多对一，商家)
            orders = ordersDao.getOrdersById(orderId);

           //2.根据订单id查询订单明细信息
            List<OrderDetailet> list = orderDetailetDao.listOrderDetailetByOrderId(orderId);
            orders.setList(list);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close();
        }
        return orders;
    }
}
