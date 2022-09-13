package com.neusoft.elmboot.service;

import com.neusoft.elmboot.po.Business;

import java.util.List;

public interface BusinessService {
    public List<Business> listBusinessByOrderTypeId(Integer orderTypeId);
}
