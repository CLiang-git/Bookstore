package com.bookstore.service;

import com.bookstore.entity.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> findCommentsByByBookId(Integer bookId);
    List<Comment> findCommentsByByUserId(Integer userId);
    void addComment(Comment comment);
    void deleteComment(Integer commentId);
}
