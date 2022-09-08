package com.neusoft.elm.service;

import com.neusoft.elm.po.Orders;

import java.util.List;

public interface OrdersService {
    public int createOrder(String userId,Integer businessId,Integer daId,Double orderTotal);
    public Orders getOrdersById(Integer orderId);
    public List<Orders> listOrdersByUserId(String userId);
}
