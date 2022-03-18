package com.bookstore.service;

import com.bookstore.entity.Book;
import com.bookstore.entity.Order;

import java.util.List;

public interface OrderService {
    List<Order> findOrderByUserId(Integer userId);
//    Book findOrderItemByOrderId(Integer orderId);
    void saveOrder(Order order);
    List<Book> findOrderBooks(List<Integer> IdList);
}
