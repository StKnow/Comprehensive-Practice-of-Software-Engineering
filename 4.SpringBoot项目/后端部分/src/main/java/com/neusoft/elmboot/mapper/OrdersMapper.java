package com.neusoft.elmboot.mapper;

import com.neusoft.elmboot.po.Orders;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrdersMapper {

    @Insert("insert into orders(userId,businessId,orderDate,orderTotal,daId,orderState) values(#{userId},#{businessId},#{orderDate},#{orderTotal},#{daId},0)")
    @Options(useGeneratedKeys=true,keyProperty="orderId",keyColumn = "orderId")
    public int saveOrders(Orders orders);

    public Orders getOrdersById(Integer orderId);

    public List<Orders> listOrdersByUserId(String userId);

    @Update("update orders set orderState=1 where orderId=#{orderId}")
    public int payOrders(Integer orderId);

    @Select("select userId from orders where orderId=#{orderId}")
    public String getUserIdByOrderId(Integer orderId);

    @Select("select businessId from orders where orderId=#{orderId}")
    public Integer getBusinessIdByOrderId(Integer orderId);

    @Select("select orderTotal from orders where orderId=#{orderId}")
    public Double getOrderTotalByOrderId(Integer orderId);
}
