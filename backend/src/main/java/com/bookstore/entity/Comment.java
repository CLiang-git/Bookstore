package com.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Comment {

    @Id
    private int commentId;

    private int bookId;
    private int userId;
    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time;

    public Comment(int commentId,int userId,String content){
        this.commentId=commentId;
        this.userId=userId;
        this.content=content;
        this.time=new Date();
    }

}
