package com.bookstore.serviceimpl;

import com.bookstore.dao.UserDao;
import com.bookstore.entity.UserInfo;
import com.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Optional<UserInfo> getUserInfo(String userName){
        return userDao.getUserByUsername(userName);
    }
}
