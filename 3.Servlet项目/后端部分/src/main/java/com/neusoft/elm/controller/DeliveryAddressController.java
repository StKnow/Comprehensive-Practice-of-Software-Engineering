package com.neusoft.elm.controller;

import com.neusoft.elm.po.DeliveryAddress;
import com.neusoft.elm.service.DeliveryAddressService;
import com.neusoft.elm.service.impl.DeliveryAddressServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DeliveryAddressController {
    public Object listDeliveryAddressByUserId(HttpServletRequest request) throws Exception{
        String userId = request.getParameter("userId");
        DeliveryAddressService service = new DeliveryAddressServiceImpl();
        List<DeliveryAddress> list= service.listDeliveryAddressByUserId(userId);
        return list;
    }

    public Object saveDeliveryAddress(HttpServletRequest request) throws Exception{
        DeliveryAddress deliveryAddress = new DeliveryAddress();
        deliveryAddress.setContactName(request.getParameter("contactName"));
        deliveryAddress.setContactSex(Integer.valueOf(request.getParameter("contactSex")));
        deliveryAddress.setContactTel(request.getParameter("contactTel"));
        deliveryAddress.setAddress(request.getParameter("address"));
        deliveryAddress.setUserId(request.getParameter("userId"));
        DeliveryAddressService service = new DeliveryAddressServiceImpl();
        int result = service.saveDeliveryAddress(deliveryAddress);
        return result;
    }

    public Object getDeliveryAddressById(HttpServletRequest request) throws Exception{
            Integer daId = Integer.valueOf(request.getParameter("daId"));
            DeliveryAddressService service = new DeliveryAddressServiceImpl();
            DeliveryAddress deliveryAddress = service.getDeliveryAddressById(daId);
            return deliveryAddress;
    }

    public Object updateDeliveryAddress(HttpServletRequest request) throws Exception{
        DeliveryAddress deliveryAddress = new DeliveryAddress();
        deliveryAddress.setContactName(request.getParameter("contactName"));
        deliveryAddress.setContactSex(Integer.valueOf(request.getParameter("contactSex")));
        deliveryAddress.setContactTel(request.getParameter("contactTel"));
        deliveryAddress.setAddress(request.getParameter("address"));
        deliveryAddress.setDaId(Integer.valueOf(request.getParameter("daId")));
        DeliveryAddressService service = new DeliveryAddressServiceImpl();
        int result = service.updateDeliveryAddress(deliveryAddress);
        return result;
    }

    public Object removeDeliveryAddress(HttpServletRequest request) throws Exception{
        Integer daId = Integer.valueOf(request.getParameter("daId"));
        DeliveryAddressService service = new DeliveryAddressServiceImpl();
        int result = service.removeDeliveryAddress(daId);
        return result;
    }
}
