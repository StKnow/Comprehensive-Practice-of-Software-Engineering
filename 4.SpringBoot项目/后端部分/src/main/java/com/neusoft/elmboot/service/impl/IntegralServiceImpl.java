package com.neusoft.elmboot.service.impl;

import com.neusoft.elmboot.mapper.IntegralMapper;
import com.neusoft.elmboot.mapper.UserMapper;
import com.neusoft.elmboot.po.IntegralFlow;
import com.neusoft.elmboot.po.User;
import com.neusoft.elmboot.service.IntegralService;
import com.neusoft.elmboot.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class IntegralServiceImpl implements IntegralService {
    @Autowired
    private IntegralMapper integralMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public Integer getIntegralById(User user) {
        if (user.getUserId() == null) {
            return 0;
        } else {
            int points = 0;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date currentTime = new Date();
            try {
                currentTime = sdf.parse(CommonUtil.getCurrentDate());
                List<IntegralFlow> flowLists = integralMapper.listPointFlowByUserId(user.getUserId());
                for (IntegralFlow integralflow : flowLists) {
                    Calendar cal = Calendar.getInstance();
                    Date flowDate = sdf.parse(integralflow.getFlowDate());
                    cal.setTime(flowDate);
                    cal.add(Calendar.MONTH, 1);
                    Date ExpirationDate = cal.getTime();
                    if (currentTime.before(ExpirationDate)) {
                        points += integralflow.getIntegralChange();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return points;
        }
    }

    @Override
    public Integer payPoints(String userId, int point) {
        if (this.getIntegralById(userMapper.getUserByUserId(userId)) < point) {
            return 0;
        } else {
            String flowDate = CommonUtil.getCurrentDate();
            integralMapper.insertFlow(userId, -point, flowDate, "商城兑换", "");
            return 1;
        }
    }

    @Override
    public List<IntegralFlow> listPointFlowByUserId(String userId) {
        return integralMapper.listPointFlowByUserId(userId);
    }
}
