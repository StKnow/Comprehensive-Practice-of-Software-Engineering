package com.neusoft.elmboot.service;

import com.neusoft.elmboot.po.IntegralFlow;
import com.neusoft.elmboot.po.User;

import java.util.List;

public interface IntegralService {
    public Integer getIntegralById(User user);

    public Integer payPoints(String userId,int point);

    public List<IntegralFlow> listPointFlowByUserId(String userId);
}
