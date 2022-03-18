package com.bookstore.repository;

import com.bookstore.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface UserRepository extends JpaRepository<UserInfo,Integer> {
}
