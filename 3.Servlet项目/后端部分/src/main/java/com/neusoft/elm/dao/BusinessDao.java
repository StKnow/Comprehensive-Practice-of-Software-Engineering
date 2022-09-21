package com.neusoft.elm.dao;

import java.util.List;

import com.neusoft.elm.po.Business;

public interface BusinessDao {
    public List<Business> listBusinessByOrderTypeId(Integer orderTypeId) throws Exception;

    public Business getBusinessById(Integer businessId) throws Exception;
}
