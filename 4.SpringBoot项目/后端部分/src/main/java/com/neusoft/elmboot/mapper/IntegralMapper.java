package com.neusoft.elmboot.mapper;

import com.neusoft.elmboot.po.IntegralFlow;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface IntegralMapper {
    @Select("select integralAmount from integral where userId=#{userId}")
    public Integer getIntegralById(String userId);

    @Update("update integral set integralAmount=#{point_now} where userId=#{userId}")
    public Integer payPoints(String userId,int point_now);

    @Insert("insert into integralFlow values(#{userId},#{integralChange},#{flowDate},#{flowDetails},#{expirationDate})")
    public Integer insertFlow(String userId,int integralChange,String flowDate,String flowDetails,String expirationDate);

    @Select("select flowDetails,integralChange,flowDate,expirationDate from integralFlow where userId=#{userId} order by flowDate DESC")
    public List<IntegralFlow> listPointFlowByUserId(String userId);



}
