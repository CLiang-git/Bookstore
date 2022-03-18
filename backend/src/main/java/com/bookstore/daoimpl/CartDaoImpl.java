package com.bookstore.daoimpl;

import com.bookstore.dao.CartDao;
import com.bookstore.entity.Book;
import com.bookstore.entity.Cart;
import com.bookstore.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CartDaoImpl implements CartDao {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public List<Cart> getCart(Integer userId){
        return cartRepository.getCart(userId);
    }

    @Override
    public Book getCartItem(Integer cartId){
        return cartRepository.getOne(cartId).getBook();
    }

    @Override
    public void saveCart(Cart cart){
        cartRepository.save(cart);
    }

    @Override
    public void deleteCart(Integer userId,Integer bookId){
        cartRepository.deleteCartByBookId(userId,bookId);
    }
}
