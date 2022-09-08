package com.neusoft.elm.controller;

import com.neusoft.elm.po.Orders;
import com.neusoft.elm.service.OrdersService;
import com.neusoft.elm.service.impl.OrdersServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.EndpointReference;

public class OrdersController {
    public Object createOrders(HttpServletRequest request) throws Exception {
        String userId = request.getParameter("userId");
        Integer businessId = Integer.valueOf(request.getParameter("businessId"));
        Integer daId = Integer.valueOf(request.getParameter("daId"));
        Double orderTotal = Double.valueOf(request.getParameter("orderTotal"));
        OrdersService service = new OrdersServiceImpl();
        int orderId = service.createOrder(userId, businessId, daId, orderTotal);
        return orderId;
    }

    public Object getOrdersById(HttpServletRequest request) throws Exception {
        Integer orderId = Integer.valueOf(request.getParameter("orderId"));
        OrdersService service = new OrdersServiceImpl();
        Orders orders = service.getOrdersById(orderId);
        return orders;
    }
}
