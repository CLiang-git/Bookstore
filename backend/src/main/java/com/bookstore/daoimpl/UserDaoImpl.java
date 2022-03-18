package com.bookstore.daoimpl;

import com.bookstore.repository.UserAuthRepository;
import com.bookstore.repository.UserRepository;
import com.bookstore.dao.UserDao;
import com.bookstore.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    UserAuthRepository userAuthRepository;

    @Autowired
    UserRepository userRepository;



    @Override
    public Optional<UserInfo> getUserByUsername(String username) {
        Integer userId = userAuthRepository.findUserIdByUserName(username);
        if(userId !=null){
            return userRepository.findById(userId);
        }
        return Optional.empty();
    }
}
