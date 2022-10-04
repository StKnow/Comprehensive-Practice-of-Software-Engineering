package com.neusoft.elmboot.service;

import com.neusoft.elmboot.po.Orders;

import java.util.List;

public interface OrdersService {
    public int createOrders(Orders orders);

    public Orders getOrdersById(Integer orderId);

    public List<Orders> listOrdersByUserId(String userId);

    public int payOrders(Integer orderId);
}
