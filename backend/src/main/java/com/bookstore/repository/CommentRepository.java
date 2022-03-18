package com.bookstore.repository;

import com.bookstore.entity.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

//@RepositoryRestResource(collectionResourceRel = "people", path = "people")
public interface CommentRepository  extends MongoRepository<Comment, Integer> {
    List<Comment> findByBookId(@Param("bookId") int bookId);
    List<Comment> findByUserId(@Param("userId") int userId);
    List<Comment> findByCommentId(@Param("commentId") int commentId);
}
