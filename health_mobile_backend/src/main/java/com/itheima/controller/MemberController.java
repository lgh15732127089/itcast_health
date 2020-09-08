package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.itheima.common.MessageConst;
import com.itheima.common.MessageConstant;
import com.itheima.common.RedisConst;
import com.itheima.entity.Result;
import com.itheima.pojo.Member;
import com.itheima.service.MemberService;
import com.itheima.service.OrderService;
import com.itheima.service.ValidateCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;

/**
 * @author : 光辉的mac
 * @ClassName MemberController
 * @date : 2020/9/4 19:39
 */
@RestController
@RequestMapping("/member")
public class MemberController {

    @Reference
    private ValidateCodeService validateCodeService;

    @Reference
    private MemberService memberService;

    @Autowired
    private JedisPool jedisPool;

    @RequestMapping("/login")
    public Result login(HttpServletResponse response, @RequestBody HashMap<String,String> map){
        //获取电话和验证码
        String telephone = map.get("telephone");
        String validateCode = map.get("validateCode");
        //从redis中校验验证码
        if (!validateCodeService.checkValidateCode(telephone, RedisConst.SENDTYPE_LOGIN,validateCode)){
            return new Result(false, MessageConstant.SEND_VALIDATECODE_FAIL);
        }
        //判断是否是会员
        Member member = memberService.findByTelephone(telephone);
        if (member == null){
            member = new Member();
            member.setPhoneNumber(telephone);
            member.setRegTime(new Date());

            memberService.addMember(member);
        }
        //3、向客户端写入Cookie，内容为用户手机号
        Cookie cookie = new Cookie("member_login_telephone",telephone);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24 * 30);
        //将Cookie写入到客户端浏览器
        response.addCookie(cookie);

        //4、将会员信息保存到Redis，使用手机号作为key，保存时长为30分钟
        Jedis jedis = jedisPool.getResource();
        jedis.setex(telephone,60 * 30, JSON.toJSON(member).toString());
        return new Result(true,MessageConstant.LOGIN_SUCCESS);


    }
}
