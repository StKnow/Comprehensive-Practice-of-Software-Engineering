package com.neusoft.elmboot.controller;


import com.neusoft.elmboot.po.Food;
import com.neusoft.elmboot.po.IntegralFlow;
import com.neusoft.elmboot.po.User;
import com.neusoft.elmboot.service.FoodService;
import com.neusoft.elmboot.service.IntegralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/IntegralController")
public class IntegralController {
    @Autowired
    private IntegralService integralService;

    @RequestMapping("getPointsById")
    public Integer getIntegralById(User user) throws Exception {
        return integralService.getIntegralById(user);
    }

    @RequestMapping("payPoints")
    public Integer payPoints(String userId,int point) throws Exception {
        return integralService.payPoints(userId,point);
    }

    @RequestMapping("listPointFlowByUserId")
    public List<IntegralFlow> listPointFlowByUserId(String userId) throws Exception{
        return integralService.listPointFlowByUserId(userId);
    }


}
