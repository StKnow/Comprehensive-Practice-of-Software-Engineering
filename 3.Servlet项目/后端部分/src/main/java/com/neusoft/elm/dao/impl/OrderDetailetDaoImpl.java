package com.neusoft.elm.dao.impl;

import com.neusoft.elm.dao.OrderDetailetDao;
import com.neusoft.elm.po.OrderDetailet;
import com.neusoft.elm.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class OrderDetailetDaoImpl implements OrderDetailetDao {
    private Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    @Override
    public int saveOrderDetailetBatch(List<OrderDetailet> list) throws Exception {
        int result = 0;
        StringBuffer stringBuffer = new StringBuffer("insert into orderDetailet(orderId,foodId,quantity) values");
        for(OrderDetailet od : list) {
            stringBuffer.append("("+od.getOrderId()+","+od.getFoodId()+","+od.getQuantity()+"),");
        }
        //去掉sql中最后一个逗号
        stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        String sql = stringBuffer.toString();
        try {
            con = DBUtil.getConnection();
            pst = con.prepareStatement(sql);
            result = pst.executeUpdate();
        }finally {
            DBUtil.close(pst);
        }
        return result;
    }
}
