package com.neusoft.elm.dao;

import com.neusoft.elm.po.OrderDetailet;

import java.util.List;

public interface OrderDetailetDao {
    public int saveOrderDetailetBatch(List<OrderDetailet> list) throws Exception;
    public List<OrderDetailet> listOrderDetailetByOrderId(Integer orderId) throws Exception;
}
