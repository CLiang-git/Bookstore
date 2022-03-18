package com.bookstore.controller;

import com.bookstore.entity.Order;
import com.bookstore.service.OrderService;
import com.bookstore.entity.Book;
import com.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.jms.core.JmsTemplate;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BookService bookService;

    @Autowired
    WebApplicationContext webapplicationContext;

    @RequestMapping("/getOrders")
    public List<Order> getOrder(@RequestParam("userId") Integer userId){
        return orderService.findOrderByUserId(userId);
    }

    @RequestMapping("/getOrderBooks")
    public List<Book> getOrderBooks(@RequestParam List<Integer> IdList){
        System.out.println(IdList);
        return orderService.findOrderBooks(IdList);

    }

//    @RequestMapping("/getOrderBook")
//    public Book getOrderItem(@RequestParam("Id") Integer Id){
//        return orderService.findOrderBook(Id);
//    }

//    @RequestMapping("/addOrder")
//    public String addOrder(@RequestParam Integer orderId,@RequestParam Integer userId,@RequestParam Integer bookId){
//        JmsTemplate jmsTemplate = webapplicationContext.getBean(JmsTemplate.class);
//        Order order=new Order();
//        order.setOrderId(orderId);
//        order.setUserId(userId);
//        Book book=bookService.findBookById(bookId);
//        order.setBook(book);
//        jmsTemplate.convertAndSend("order", order);
//        return "order is adding";
//    }

}
