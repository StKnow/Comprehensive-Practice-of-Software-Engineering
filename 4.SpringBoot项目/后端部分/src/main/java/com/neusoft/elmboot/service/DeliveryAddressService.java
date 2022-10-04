package com.neusoft.elmboot.service;

import com.neusoft.elmboot.po.DeliveryAddress;

import java.util.List;

public interface DeliveryAddressService {
    public List<DeliveryAddress> listDeliveryAddressByUserId(String userId);

    public DeliveryAddress getDeliveryAddressById(Integer daId);

    public int saveDeliveryAddress(DeliveryAddress deliveryAddress);

    public int updateDeliveryAddress(DeliveryAddress deliveryAddress);

    public int removeDeliveryAddress(Integer daId);
}
