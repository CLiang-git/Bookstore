package com.bookstore.service;

import com.bookstore.entity.Book;
import com.bookstore.entity.Image;
import com.bookstore.entity.vo.BookStatistic;

import java.util.List;

public interface BookService {

    Book findBookById(Integer id);

    List<Book> getBooks();

    Image findImageByBookId(Integer id);

    BookStatistic getBookStatistic();

    List<Book> searchBooksBy(String keyword);
}
