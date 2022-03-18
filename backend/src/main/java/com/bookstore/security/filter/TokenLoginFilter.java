package com.bookstore.security.filter;

import com.bookstore.entity.dto.ResultDTO;
import com.bookstore.security.JwtTokenUtils;
import com.bookstore.security.ResponseUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

//用户登录过滤器
public class TokenLoginFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    public TokenLoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        this.setPostOnly(false);
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
        try {
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            username = username != null ? username.trim() : "";
            password = password != null ? password : "";
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>()));
        } catch (UsernameNotFoundException e) {
            System.out.println("username don't exist");
            throw e;//用户名不存在
        } catch (Exception e) {
            logger.error("Unexpected error when authenticating", e);//未知错误
            System.out.println("Unexpected error when authenticating");
            throw e;
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication auth) throws IOException, ServletException {
        System.out.println("Filter: successfulAuthentication ^_^");
        User user = (User) auth.getPrincipal();
        String authorities = user.getAuthorities().size() > 0 ? user.getAuthorities().toString().replaceAll("(?:\\[|null|\\]| +)", "") : user.getAuthorities().toString();
        System.out.println(authorities);
        String token = JwtTokenUtils.createToken(user.getUsername(), authorities);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("userRoles", user.getAuthorities());
        map.put("userName", user.getUsername());
        ResponseUtils.out(response, ResultDTO.success(map));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        System.out.println("Filter: unsuccessfulAuthentication *_*");
        ResponseUtils.out(response, ResultDTO.error(e.getMessage()));
    }
}
