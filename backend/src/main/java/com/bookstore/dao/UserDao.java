package com.bookstore.dao;

import com.bookstore.entity.UserInfo;

import java.util.Optional;

public interface UserDao {

    Optional<UserInfo> getUserByUsername(String username);
}
