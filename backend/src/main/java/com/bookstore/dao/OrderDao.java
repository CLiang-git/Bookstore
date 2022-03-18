package com.bookstore.dao;

import com.bookstore.entity.Order;
import com.bookstore.entity.Book;

import java.util.List;

public interface OrderDao {
    List<Order> getOrder(Integer userId);
//    Book getOrderItem(Integer orderId);
    void saveOrder(Order order);

    Book getOrderBook(Integer Id);
}
