package com.neusoft.elmboot.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface VirtualWalletMapper {
    @Select("select balance from virtualwallet where walletId=#{walletId}")
    public Double getBalanceById(Integer walletId);

    @Update("update virtualwallet set balance=#{newBalance} where walletId=#{walletId}")
    public int updateBalance(Integer walletId,Double newBalance);

    @Select("select userId from virtualwallet where walletId=#{walletId}")
    public String getUserIdByWalletId(Integer walletId);

    @Select("select businessId from virtualwallet where walletId=#{walletId}")
    public String getBusinessIdByWalletId(Integer walletId);
}
