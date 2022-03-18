package com.bookstore.config;

import com.bookstore.security.AuthEntryPoint;
import com.bookstore.security.PasswordEncoder;
import com.bookstore.security.LogoutUtils;
import com.bookstore.security.filter.TokenAuthFilter;
import com.bookstore.security.filter.TokenLoginFilter;
import com.bookstore.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    LogoutUtils logoutUtils;

    @Autowired
    UserDetailService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling()
                .authenticationEntryPoint(new AuthEntryPoint())
                .and().authorizeRequests()
                .anyRequest().authenticated()
                .and().csrf().disable()
                .logout().logoutUrl("/logout").logoutSuccessHandler(logoutUtils)
                .and()
                .addFilter(new TokenLoginFilter(authenticationManager()))
                .addFilter(new TokenAuthFilter(authenticationManager()))
                .cors().and()
                .httpBasic();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }
}
