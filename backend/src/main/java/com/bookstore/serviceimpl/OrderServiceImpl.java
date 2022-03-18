package com.bookstore.serviceimpl;

import com.bookstore.dao.OrderDao;
import com.bookstore.entity.Book;
import com.bookstore.entity.Order;
import com.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;

    @Override
    public List<Order> findOrderByUserId(Integer userId){
        return orderDao.getOrder(userId);
    }

//    @Override
//    public Book findOrderItemByOrderId(Integer orderId){
//        return orderDao.getOrderItem(orderId);
//    }

    @Override
    public void saveOrder(Order order){
        orderDao.saveOrder(order);
    }

    @Override
    public List<Book> findOrderBooks(List<Integer> IdList){
        List<Book> books=new ArrayList<Book>();
        for (int i=0;i<IdList.size();i++){
            books.add(i,orderDao.getOrderBook(IdList.get(i)));
        }
        return books;
    }
}
