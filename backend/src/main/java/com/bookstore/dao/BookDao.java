package com.bookstore.dao;

import com.bookstore.entity.Book;
import com.bookstore.entity.Image;

import java.util.List;

public interface BookDao {
    Book findOne(Integer id);

    List<Book> getBooks();

    Image getBookImage(Integer id);

    Integer getBookNumber();

    Integer getBookInventorySum();
}
