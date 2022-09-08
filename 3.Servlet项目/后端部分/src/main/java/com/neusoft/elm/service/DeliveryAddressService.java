package com.neusoft.elm.service;

import com.neusoft.elm.po.DeliveryAddress;

import java.util.List;

public interface DeliveryAddressService {
    public List<DeliveryAddress> listDeliveryAddressByUserId(String userId);
    public int saveDeliveryAddress(DeliveryAddress deliveryAddress);
    public DeliveryAddress getDeliveryAddressById(Integer daId);
    public int updateDeliveryAddress(DeliveryAddress deliveryAddress);
    public int removeDeliveryAddress(Integer daId);
}
