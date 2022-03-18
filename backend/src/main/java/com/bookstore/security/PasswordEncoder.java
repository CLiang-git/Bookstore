package com.bookstore.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoder extends BCryptPasswordEncoder {

    //密码加密
    @Override
    public String encode(CharSequence rawPassword) {
        //暂时不做任何加密，方便看密码
        return rawPassword.toString();
    }

    //比较加密和未加密的密码是否相同
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        if (rawPassword == null) {
            throw new IllegalArgumentException("rawPassword cannot be null");
        }
        if (encodedPassword == null || encodedPassword.length() == 0) {
            throw new IllegalArgumentException("encodedPassword is null");
        }
        return encodedPassword.equals(rawPassword);//如果加密了需要调用encode再比较
    }
}