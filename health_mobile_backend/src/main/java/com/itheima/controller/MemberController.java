package com.itheima.controller;

import com.itheima.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;

/**
 * @author : 光辉的mac
 * @ClassName MemberController
 * @date : 2020/9/4 19:39
 */
@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private JedisPool jedisPool;

    @RequestMapping("/login")
    public Result login(@RequestBody HashMap<String,String> map){
        //获取电话和验证码
        String telephone = map.get("telephone");
        String validateCode = map.get("validateCode");
        //从redis中获取验证码

        return null;
    }
}
