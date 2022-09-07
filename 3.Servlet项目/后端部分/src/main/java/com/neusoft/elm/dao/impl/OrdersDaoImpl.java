package com.neusoft.elm.dao.impl;

import com.neusoft.elm.dao.OrdersDao;
import com.neusoft.elm.po.Orders;
import com.neusoft.elm.util.CommonUtil;
import com.neusoft.elm.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OrdersDaoImpl implements OrdersDao {
    private Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    @Override
    public int saveOrders(Orders orders) throws Exception {
        int orderId = 0;
        String sql = "insert into orders values(null,?,?,?,?,?,0)";
        try {
            con = DBUtil.getConnection();
            pst = con.prepareStatement(sql);
            pst.setString(1, orders.getUserId());
            pst.setInt(2, orders.getBusinessId());
            pst.setString(3, CommonUtil.getCurrentDate()
            );
            pst.setDouble(4, orders.getOrderTotal());
            pst.setInt(5, orders.getDaId());
            rs = pst.getGeneratedKeys();    //获取自增长列值(一行一列)
            if(rs.next()) {
                orderId = rs.getInt(1);
            }
        } finally {
            DBUtil.close(pst);
        }
        return orderId;
    }
}
