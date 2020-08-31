package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.service.UserService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author : 光辉的mac
 * @ClassName UserServiceImpl
 * @date : 2020/8/28 11:07
 */
//使用lombok进行日志处理
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Override
    public Boolean login(String username, String password) {
        log.debug("输入的用户名和密码是"+username + password);
        if ("admin".equals(username) && "123".equals(password)){
            return true;
        }
        return false;

    }
}
