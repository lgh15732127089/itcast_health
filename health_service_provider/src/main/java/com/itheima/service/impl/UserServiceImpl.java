package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.service.UserService;

/**
 * @author : 光辉的mac
 * @ClassName UserServiceImpl
 * @date : 2020/8/28 11:07
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public Boolean login(String username, String password) {
        System.out.println(username+password);
        return null;
    }
}
