package com.bookstore.controller;

import com.bookstore.entity.Book;
import com.bookstore.entity.Cart;
import com.bookstore.service.BookService;
import com.bookstore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

@RestController
@Scope("session")
public class CartController {

    @Autowired
    WebApplicationContext webApplicationContext;
//    private CartService cartService;

    @Autowired
    BookService bookService;

    @RequestMapping("/getCart")
    public List<Cart> getCart(@RequestParam("userId") Integer userId){
        CartService cartService=webApplicationContext.getBean(CartService.class);
        return cartService.findCartByUserId(userId);
    }

    @RequestMapping("/getCartItem")
    public Book getCartItem(@RequestParam("cartId") Integer cartId){
        CartService cartService=webApplicationContext.getBean(CartService.class);
        return cartService.findCartItemByCartId(cartId);
    }

    @RequestMapping("/addCart")
    public boolean addCart(@RequestParam Integer userId,@RequestParam Integer bookId){
        System.out.println("addCart request: userId="+userId+", bookId= "+bookId);
        CartService cartService=webApplicationContext.getBean(CartService.class);
        Cart cart=new Cart();
        cart.setUserId(userId);
        Book book=bookService.findBookById(bookId);
        cart.setBook(book);
        cartService.addCartItem(cart);
        return true;
    }

    @RequestMapping("/deleteCart")
    public void deleteCart(@RequestParam Integer userId,@RequestParam Integer bookId){
        CartService cartService=webApplicationContext.getBean(CartService.class);
        cartService.deleteCartItem(userId,bookId);
    }

}
