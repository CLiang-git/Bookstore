package com.bookstore.repository;

import com.bookstore.entity.Order;
import com.bookstore.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface OrderItemRepository extends JpaRepository<OrderItem,Integer> {

}
