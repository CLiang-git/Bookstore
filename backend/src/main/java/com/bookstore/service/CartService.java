package com.bookstore.service;

import com.bookstore.entity.Book;
import com.bookstore.entity.Cart;

import java.util.List;

public interface CartService {
    List<Cart> findCartByUserId(Integer userId);

    Book findCartItemByCartId(Integer cartId);

    void addCartItem(Cart cart);

    void deleteCartItem(Integer userId,Integer bookId);
}
