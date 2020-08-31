package com.itheima.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author : 光辉的mac
 * @ClassName User
 * @date : 2020/8/28 11:03
 */
@Data
public class User implements Serializable {
    private String username;
    private String password;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
