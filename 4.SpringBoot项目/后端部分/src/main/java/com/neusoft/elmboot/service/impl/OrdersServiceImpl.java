package com.neusoft.elmboot.service.impl;

import com.neusoft.elmboot.mapper.*;
import com.neusoft.elmboot.po.Cart;
import com.neusoft.elmboot.po.OrderDetailet;
import com.neusoft.elmboot.po.Orders;
import com.neusoft.elmboot.po.User;
import com.neusoft.elmboot.service.IntegralService;
import com.neusoft.elmboot.service.OrdersService;
import com.neusoft.elmboot.service.VirtualWalletService;
import com.neusoft.elmboot.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private OrderDetailetMapper orderDetailetMapper;
    @Autowired
    private IntegralMapper integralMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BusinessMapper businessMapper;
    @Autowired
    private VirtualWalletService virtualWalletService;
    @Autowired
    private IntegralService integralService;


    @Override
    @Transactional
    public int createOrders(Orders orders) {
        //1、查询当前用户购物车中当前商家的所有食品
        Cart cart = new Cart();
        cart.setUserId(orders.getUserId());
        cart.setBusinessId(orders.getBusinessId());
        List<Cart> cartList = cartMapper.listCart(cart);

        //2、创建订单（返回生成的订单编号）
        orders.setOrderDate(CommonUtil.getCurrentDate());
        ordersMapper.saveOrders(orders);
        int orderId = orders.getOrderId();

        //3、批量添加订单明细
        List<OrderDetailet> list = new ArrayList<>();
        for(Cart c : cartList) {
            OrderDetailet od = new OrderDetailet();
            od.setOrderId(orderId);
            od.setFoodId(c.getFoodId());
            od.setQuantity(c.getQuantity());
            list.add(od);
        }
        orderDetailetMapper.saveOrderDetailetBatch(list);

        //4、从购物车表中删除相关食品信息
        cartMapper.removeCart(cart);

        return orderId;

    }
    @Override
    public Orders getOrdersById(Integer orderId) {
        return ordersMapper.getOrdersById(orderId);
    }

    @Override
    public List<Orders> listOrdersByUserId(String userId){
        return ordersMapper.listOrdersByUserId(userId);
    }

    @Override
    public int payOrders(Integer orderId) {
        String userId = ordersMapper.getUserIdByOrderId(orderId);
        Integer businessId = ordersMapper.getBusinessIdByOrderId(orderId);
        Integer fromWalletId = userMapper.getWalletIdByUserId(userId);
        Integer toWalletId = businessMapper.getWalletIdByBusinessId(businessId);
        Double orderTotal = ordersMapper.getOrderTotalByOrderId(orderId);
        virtualWalletService.transfer(fromWalletId, toWalletId, orderTotal);
        Integer integralChange = orderTotal.intValue();
        String CurrentTime = CommonUtil.getCurrentDate();
        String ExpirationTime = CommonUtil.getMonthLaterDate();
        integralMapper.insertFlow(userId,integralChange,CurrentTime,"订单积分",ExpirationTime);

        return ordersMapper.payOrders(orderId);
    }

    @Override
    public Integer usePointPayOrders(Integer orderId){
        Double orderTotal = ordersMapper.getOrderTotalByOrderId(orderId);
        String userId = ordersMapper.getUserIdByOrderId(orderId);
        User user = userMapper.getUserByUserId(userId);
        Integer points = integralService.getIntegralById(user);
        Double finalBill = orderTotal-(points/100);
        String CurrentTime = CommonUtil.getCurrentDate();
        System.out.println("----------------"+finalBill);
        integralMapper.insertFlow(userId,-points,CurrentTime,"积分抵扣","");
        return ordersMapper.usePointPayOrders(orderId,finalBill);
    }
}
