package com.bookstore.dao;

import com.bookstore.entity.Book;
import com.bookstore.entity.Cart;

import java.util.List;

public interface CartDao {
    List<Cart> getCart(Integer userId);
    Book getCartItem(Integer cartId);
    void saveCart(Cart cart);
    void deleteCart(Integer userId,Integer bookId);
}
