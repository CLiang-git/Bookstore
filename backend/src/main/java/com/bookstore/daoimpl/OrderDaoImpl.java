package com.bookstore.daoimpl;

import com.bookstore.entity.Order;
import com.bookstore.repository.OrderItemRepository;
import com.bookstore.repository.OrderRepository;
import com.bookstore.dao.OrderDao;
import com.bookstore.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public List<Order> getOrder(Integer userId){
        return orderRepository.getOrder(userId);
    }

//    @Override
//    public Book getOrderItem(Integer orderId){
//        return orderRepository.getOne(orderId).getBook();
//    }

    @Override
    public void saveOrder(Order order){
        orderRepository.save(order);
    }

    @Override
    public Book getOrderBook(Integer Id){
        return orderItemRepository.getOne(Id).getBook();
    }
}
