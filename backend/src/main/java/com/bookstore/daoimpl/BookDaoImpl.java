package com.bookstore.daoimpl;

import com.alibaba.fastjson.JSONArray;
import com.bookstore.repository.BookRepository;
import com.bookstore.dao.BookDao;
import com.bookstore.entity.Book;
import com.bookstore.entity.Image;
import com.bookstore.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    RedisUtil redisUtil;

    @Override
    public Book findOne(Integer id){
        Book book = null;
        System.out.println("Searching Book: " + id + " in Redis");
        Object p = redisUtil.get("book" + id);
        if (p == null) {
            System.out.println("Book: " + id + " is not in Redis");
            System.out.println("Searching Book: " + id + " in DB");
            book = bookRepository.getOne(id);
            redisUtil.set("book" + id, JSONArray.toJSON(book));
        } else {
            book = JSONArray.parseObject(p.toString(), Book.class);
            System.out.println("Book: " + id + " is in Redis");

        }
        return book;
    }

    @Override
    public List<Book> getBooks() {
        return bookRepository.getBooks();
    }

    @Override
    public Image getBookImage(Integer id) {
        return bookRepository.getOne(id).getBookImage();
    }

    @Override
    public Integer getBookNumber() {
        return bookRepository.getBookNumber();
    }

    @Override
    public Integer getBookInventorySum() {
        return bookRepository.getBookInventorySum();
    }
}
