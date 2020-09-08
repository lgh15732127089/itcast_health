package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.dao.UserDao;
import com.itheima.pojo.SysUser;
import com.itheima.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author : 光辉的mac
 * @ClassName UserServiceImpl
 * @date : 2020/8/28 11:07
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    @Override
    public SysUser findUserByUsername(String username) {
        return userDao.findUserByUsername(username);
    }
}
