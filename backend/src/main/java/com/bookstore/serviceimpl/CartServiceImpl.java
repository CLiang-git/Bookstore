package com.bookstore.serviceimpl;

import com.bookstore.service.CartService;
import com.bookstore.dao.CartDao;
import com.bookstore.entity.Book;
import com.bookstore.entity.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Scope("session")
public class CartServiceImpl implements CartService {

    @Autowired
    private CartDao cartDao;

    @Override
    public List<Cart> findCartByUserId(Integer userId){
        return cartDao.getCart(userId);
    }

    @Override
    public Book findCartItemByCartId(Integer cartId){
        return cartDao.getCartItem(cartId);
    }

    @Override
    public void addCartItem(Cart cart){
        cartDao.saveCart(cart);
    }

    @Override
    public void deleteCartItem(Integer userId,Integer bookId){
        cartDao.deleteCart(userId,bookId);
    }

}
