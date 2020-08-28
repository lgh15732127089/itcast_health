package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.entity.Result;
import com.itheima.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author : 光辉的mac
 * @ClassName UserController
 * @date : 2020/8/28 11:10
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Reference
    private UserService userService;

    @RequestMapping("/login")
    @ResponseBody
    public Result login(String username,String password){
        if (userService.login(username,password)){
            return new Result(true,"成功");
        }
        return new Result(false,"失败");
    }
}
