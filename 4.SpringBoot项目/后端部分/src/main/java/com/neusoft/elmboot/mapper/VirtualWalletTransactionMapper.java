package com.neusoft.elmboot.mapper;

import com.neusoft.elmboot.domain.VirtualWalletTransaction;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface VirtualWalletTransactionMapper {
    @Insert("insert into transaction values(null,#{transactionDate},#{transactionAmount},#{transactionType},#{toWalletId},#{fromWalletId},#{userId})")
    public int saveCheckFlow(VirtualWalletTransaction virtualWalletTransaction);

    @Select("select * from transaction where userId=#{userId} order by transactionDate")
    public List<VirtualWalletTransaction> listCheckFlowByUserId(String userId);
}
