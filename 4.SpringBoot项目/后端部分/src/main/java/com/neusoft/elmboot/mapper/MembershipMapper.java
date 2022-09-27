package com.neusoft.elmboot.mapper;

import com.neusoft.elmboot.po.Membership;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MembershipMapper {
    @Insert("insert into membership values(null,#{grade},#{userId})")
    public int saveMembership(Membership membership);

    @Select("select grade from membership where userId=#{userId}")
    public Integer getMembershipById(String userId);
}

