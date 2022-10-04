package com.neusoft.elmboot.mapper;

import com.neusoft.elmboot.po.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper {
    @Insert("insert into comment values(null,#{content},#{businessId},#{userId},#{userName},#{grade})")
    public int saveComment(String content, Integer businessId, String userId, String userName, Integer grade);

    @Select("select * from comment where businessId=#{businessId}")
    public List<Comment> listCommentByBusinessId(Integer businessId);
}
