package com.neusoft.elmboot.service;

import com.neusoft.elmboot.po.Business;
import org.apache.ibatis.annotations.Insert;

import java.util.List;

public interface BusinessService {
    public List<Business> listBusinessByOrderTypeId(Integer orderTypeId);

    public Business getBusinessById(Integer businessId);
}
