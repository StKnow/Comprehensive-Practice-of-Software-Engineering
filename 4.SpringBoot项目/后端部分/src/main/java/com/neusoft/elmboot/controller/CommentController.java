package com.neusoft.elmboot.controller;

import com.neusoft.elmboot.po.Comment;
import com.neusoft.elmboot.po.User;
import com.neusoft.elmboot.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/CommentController")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @RequestMapping("/saveComment")
    public int saveComment(String content, Integer businessId, String userId) throws Exception {
        return commentService.saveComment(content, businessId, userId);
    }

    @RequestMapping("/listCommentByBusinessId")
    public List<Comment> listCommentByBusinessId(Integer businessId) throws Exception {
        System.out.println("wocoaismasihqadwioha wdqdahjdoiahdowa");
        return commentService.listCommentByBusinessId(businessId);
    }
}
