package com.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "userInfo")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class UserInfo {

    @Id
    private int userId;
    private String nickname;
    private String name;
    private String tel;
    private String address;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private List<UserRole> userRoleList;//一个用户可以有很多身份

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private UserAuth userAuth;

    public int getUserId() {
        return userId;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public String getNickname() {
        return nickname;
    }

    public String getTel() {
        return tel;
    }

    public List<UserRole> getUserRoleList() {
        return userRoleList;
    }

    public UserAuth getUserAuth() {
        return userAuth;
    }
}
