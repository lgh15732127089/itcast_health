package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.common.MessageConst;
import com.itheima.common.MessageConstant;
import com.itheima.common.RedisConst;
import com.itheima.entity.Result;
import com.itheima.service.OrderService;
import com.itheima.service.ValidateCodeService;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author : 光辉的mac
 * @ClassName OrderController
 * @date : 2020/9/5 15:38
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Reference
    private ValidateCodeService validateCodeService;

    @Reference
    private OrderService orderService;

    @RequestMapping("/submit")
    public Result submit(@RequestBody Map<String,String> map){
        //手机号
        String telephone = map.get("telephone");
        //验证码
        String validateCode = map.get("validateCode");
        //校验验证码 如果验证码错误返回
        //if (!validateCodeService.checkValidateCode(telephone, RedisConst.SENDTYPE_ORDER, validateCode)){
        //    return new Result(false, MessageConstant.VALIDATECODE_ERROR);
        //}
        return orderService.addOrderByCondition(map);
    }

    @RequestMapping("/findById")
    public Result findById(Integer id){
        Map<String,Object> map = orderService.findById(id);
        return new Result(true,MessageConstant.ORDERSETTING_SUCCESS,map);
    }
}
