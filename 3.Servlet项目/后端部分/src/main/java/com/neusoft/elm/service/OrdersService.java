package com.neusoft.elm.service;

import com.neusoft.elm.po.Orders;

public interface OrdersService {
    public int createOrder(String userId,Integer businessId,Integer daId,Double orderTotal);
    public Orders getOrdersById(Integer orderId);
}
