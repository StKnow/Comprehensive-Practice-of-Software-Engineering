package com.neusoft.elmboot.service;

import com.neusoft.elmboot.po.Comment;

import java.util.List;

public interface CommentService {
    public int saveComment(String content, Integer businessId, String userId);

    public List<Comment> listCommentByBusinessId(Integer businessId);
}
