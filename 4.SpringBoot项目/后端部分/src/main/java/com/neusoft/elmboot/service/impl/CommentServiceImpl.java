package com.neusoft.elmboot.service.impl;

import com.neusoft.elmboot.mapper.CommentMapper;
import com.neusoft.elmboot.po.Comment;
import com.neusoft.elmboot.service.CommentService;
import com.neusoft.elmboot.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public int saveComment(String content, Integer businessId, String userId, String userName, Integer grade) {
        String commentDate = CommonUtil.getCurrentDate();
        return commentMapper.saveComment(content, businessId, userId, userName, grade,commentDate);
    }

    @Override
    public List<Comment> listCommentByBusinessId(Integer businessId) {
        return commentMapper.listCommentByBusinessId(businessId);
    }
}
