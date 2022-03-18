package com.bookstore.controller;

import com.bookstore.entity.Comment;
import com.bookstore.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping("/getCommentByBook")
    public List<Comment> getCommentByBook(@RequestParam Integer bookId){
        return commentService.findCommentsByByBookId(bookId);
    }

    @RequestMapping("/getCommentByUser")
    public List<Comment> getCommentByUser(@RequestParam Integer userId){
        return commentService.findCommentsByByUserId(userId);
    }

    @RequestMapping("/addComment")
    public void addComment(@RequestParam Integer commentId,@RequestParam Integer bookId,@RequestParam Integer userId,@RequestParam String content){
        Comment comment=new Comment(commentId,userId,content);
        commentService.addComment(comment);
    }

    @RequestMapping("/deleteComment")
    public void deleteComment(@RequestParam Integer commentId){
        commentService.deleteComment(commentId);
    }
}
