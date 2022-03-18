package com.bookstore.service;

import com.bookstore.entity.UserInfo;

import java.util.Optional;

public interface UserService {

    Optional<UserInfo> getUserInfo(String userName);
}
