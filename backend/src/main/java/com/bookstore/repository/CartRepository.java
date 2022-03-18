package com.bookstore.repository;

import com.bookstore.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface CartRepository extends JpaRepository<Cart,Integer> {

    //@Query(value = "select a.cartId, b.author from Cart a , Book b where a.userId = ?1 and a.bookId=b.bookId")
    @Query(value = "from Cart where userId = ?1")
    List<Cart> getCart(Integer userId);

    @Query(value = "delete from Cart where userId=?1 and book.bookId=?2")
    void deleteCartByBookId(Integer userId,Integer bookId);
}
