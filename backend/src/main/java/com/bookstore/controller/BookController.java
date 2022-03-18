package com.bookstore.controller;

import com.bookstore.entity.Book;
import com.bookstore.entity.Image;
import com.bookstore.service.BookService;
import com.bookstore.entity.vo.BookStatistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/getBooks")
    public List<Book> getBooks(@RequestBody Map<String, String> params) {
        return bookService.getBooks();
    }

    @RequestMapping("/getBook")
    public Book getBook(@RequestParam("id") Integer id){
        return bookService.findBookById(id);
    }

    @RequestMapping("/getBookImage")
    public Image getBookImage(@RequestParam("id") Integer id){
        return bookService.findImageByBookId(id);
    }

    @RequestMapping("/getBookStatistic")
    @PreAuthorize("hasRole('ADMIN')")
    public BookStatistic getBookStatistic(@RequestBody Map<String, String> params) {return bookService.getBookStatistic();}

    @GetMapping("/getBooks")
    public List<Book> searchBooksBy(@RequestParam("keyword") String keyword) { return bookService.searchBooksBy(keyword); }

}
