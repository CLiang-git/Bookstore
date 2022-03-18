package com.bookstore.serviceimpl;

import com.bookstore.dao.BookDao;
import com.bookstore.entity.Book;
import com.bookstore.entity.Image;
import com.bookstore.lucene.Searcher;
import com.bookstore.service.BookService;
import com.bookstore.entity.vo.BookStatistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Override
    public Book findBookById(Integer id){
        return bookDao.findOne(id);
    }

    @Override
    public List<Book> getBooks() {
        return bookDao.getBooks();
    }

    @Override
    public Image findImageByBookId(Integer id) {
        return bookDao.getBookImage(id);
    }

    @Override
    public BookStatistic getBookStatistic() {
        BookStatistic statistic = new BookStatistic();
        statistic.setBookNumber(bookDao.getBookNumber());
        statistic.setBookInventorySum(bookDao.getBookInventorySum());
        return  statistic;
    }

    @Override
    public List<Book> searchBooksBy(String keyword){
        List<Integer> bookIdList = Searcher.searchBooksBy(keyword);
        List<Book> books = new ArrayList<>();
        System.out.println("Get bookIdList from lucene: "+ bookIdList);
        for (Integer bookId : bookIdList) {
            books.add(bookDao.findOne(bookId));
        }
        return books;
    }
}
