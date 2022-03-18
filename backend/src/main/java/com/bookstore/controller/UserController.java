package com.bookstore.controller;

import com.bookstore.entity.UserInfo;
import com.bookstore.security.SecurityUtils;
import com.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/checkAuth")
    public UserInfo checkAuth(){
        System.out.println("checkAuth");
        Optional<UserInfo> userInfoOptional =  userService.getUserInfo(SecurityUtils.getCurrentUsername());
        return userInfoOptional.orElse(null);
    }

}
