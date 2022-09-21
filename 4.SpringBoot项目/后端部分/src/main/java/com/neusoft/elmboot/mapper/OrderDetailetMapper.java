package com.neusoft.elmboot.mapper;

import com.neusoft.elmboot.po.OrderDetailet;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderDetailetMapper {
    public int saveOrderDetailetBatch(List<OrderDetailet> list);

    public List<OrderDetailet> listOrderDetailetByOrderId(Integer orderOd);
}
