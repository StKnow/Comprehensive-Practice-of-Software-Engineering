package com.neusoft.elm.dao;

import com.neusoft.elm.po.DeliveryAddress;

import java.util.List;

public interface DeliveryAddressDao {
    public List<DeliveryAddress> listDeliveryAddressByUserId(String userId) throws Exception;
    public int saveDeliveryAddress(DeliveryAddress deliveryAddress) throws Exception;
    public DeliveryAddress getDeliveryAddressById(Integer daId) throws Exception;
    public int updateDeliveryAddress(DeliveryAddress deliveryAddress) throws Exception;
    public int removeDeliveryAddress(Integer daId) throws Exception;
}
