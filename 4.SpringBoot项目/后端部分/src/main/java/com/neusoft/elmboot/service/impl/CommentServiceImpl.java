package com.neusoft.elmboot.service.impl;

import com.neusoft.elmboot.mapper.CommentMapper;
import com.neusoft.elmboot.po.Comment;
import com.neusoft.elmboot.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public int saveComment(String content, Integer businessId, String userId) {
        return commentMapper.saveComment(content, businessId, userId);
    }

    @Override
    public List<Comment> listCommentByBusinessId(Integer businessId) {
        return commentMapper.listCommentByBusinessId(businessId);
    }
}
