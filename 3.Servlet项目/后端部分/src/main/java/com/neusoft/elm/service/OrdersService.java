package com.neusoft.elm.service;

public interface OrdersService {
    public int createOrder(String userId,Integer businessId,Integer daId,Double orderTotal);
}
