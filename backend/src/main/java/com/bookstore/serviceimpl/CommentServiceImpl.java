package com.bookstore.serviceimpl;

import com.bookstore.dao.CommentDao;
import com.bookstore.entity.Comment;
import com.bookstore.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Override
    public List<Comment> findCommentsByByBookId(Integer bookId) {
        return commentDao.getCommentsByBookId(bookId);
    }

    @Override
    public List<Comment> findCommentsByByUserId(Integer userId) {
        return commentDao.getCommentsByUserId(userId);
    }

    @Override
    public void addComment(Comment comment) {
        commentDao.saveComment(comment);
    }

    @Override
    public void deleteComment(Integer commentId) {
        commentDao.deleteComment(commentId);
    }
}
