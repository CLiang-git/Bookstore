package com.bookstore.dao;

import com.bookstore.entity.Cart;
import com.bookstore.entity.Comment;

import java.util.List;

public interface CommentDao {
    List<Comment> getCommentsByBookId(Integer bookId);
    List<Comment> getCommentsByUserId(Integer userId);
    void saveComment(Comment comment);
    void deleteComment(Integer commentId);
}
