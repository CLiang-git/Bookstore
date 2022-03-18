package com.bookstore.daoimpl;

import com.bookstore.dao.CommentDao;
import com.bookstore.entity.Comment;
import com.bookstore.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentDaoImpl implements CommentDao {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comment> getCommentsByBookId(Integer bookId) {
        return commentRepository.findByBookId(bookId);
    }

    @Override
    public List<Comment> getCommentsByUserId(Integer userId) {
        return commentRepository.findByUserId(userId);
    }

    @Override
    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Integer commentId){
        commentRepository.deleteById(commentId);
    }
}
