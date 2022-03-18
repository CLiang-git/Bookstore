package com.bookstore.listener;

import com.bookstore.entity.Order;
import com.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;

public class OrderListener {
    @Autowired
    private OrderService orderService;

    @JmsListener(destination = "order")
    public void handleOrderMessage(Order order){
        orderService.saveOrder(order);
    }
}
