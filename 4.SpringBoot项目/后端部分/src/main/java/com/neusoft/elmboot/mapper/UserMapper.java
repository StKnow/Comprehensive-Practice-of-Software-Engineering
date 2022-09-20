package com.neusoft.elmboot.mapper;

import com.neusoft.elmboot.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from user where userId=#{userId} and password=#{password}")
    public User getUserByIdByPass(User user);

}
