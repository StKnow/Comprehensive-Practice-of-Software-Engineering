package com.neusoft.elm.dao.impl;

import com.neusoft.elm.dao.DeliveryAddressDao;
import com.neusoft.elm.po.DeliveryAddress;
import com.neusoft.elm.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DeliveryAddressDaoImpl implements DeliveryAddressDao {
    private Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    public List<DeliveryAddress> listDeliveryAddressByUserId(String userId) throws Exception{
        List<DeliveryAddress> list = new ArrayList<>();
        String sql = "select * from deliveryAddress where userId=? order by daId";
        try {
            con = DBUtil.getConnection();
            pst = con.prepareStatement(sql);
            pst.setString(1, userId);
            rs = pst.executeQuery();
            while(rs.next()) {
                DeliveryAddress deliveryAddress = new DeliveryAddress();
                deliveryAddress.setDaId(rs.getInt("daId"));
                deliveryAddress.setContactName(rs.getString("contactName"));
                deliveryAddress.setContactSex(rs.getInt("contactSex"));
                deliveryAddress.setContactTel(rs.getString("contactTel"));
                deliveryAddress.setAddress(rs.getString("address"));
                deliveryAddress.setUserId(rs.getString("userId"));
                list.add(deliveryAddress);
            }
        }finally {
            DBUtil.close(rs,pst);
        }
        return list;

    }

}
