package com.itheima.service;

import com.itheima.pojo.SysUser;

/**
 * @author : 光辉的mac
 * @ClassName UserService
 * @date : 2020/8/28 11:06
 */
public interface UserService {

    SysUser findUserByUsername(String username);

    void editPasswordByUsername(String username, String password);
}
