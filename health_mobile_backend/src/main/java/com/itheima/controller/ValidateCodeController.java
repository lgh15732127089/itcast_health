package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.common.MessageConstant;
import com.itheima.common.RedisConst;
import com.itheima.entity.Result;
import com.itheima.service.ValidateCodeService;
import com.itheima.utils.RedisMessageConstant;
import com.itheima.utils.SMSUtils;
import com.itheima.utils.ValidateCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author : 光辉的mac
 * @ClassName ValidateCodeController
 * @date : 2020/9/4 19:19
 */
@RestController
@RequestMapping("/validateCode")
public class ValidateCodeController {

    @Reference
    private ValidateCodeService validateCodeService;

    @RequestMapping("/send4Login")
    public Result send4Login(String telephone) {
        if (telephone != null && telephone.length() > 0) {

            //获取验证码
            String code = String.valueOf(ValidateCodeUtils.generateValidateCode(4));
            if (code != null && code.length() > 0) {
                try {
                    //将验证码保存到redis数据库并发送到用户
                    validateCodeService.sendValidateCodeShortMessage(telephone, RedisConst.SENDTYPE_LOGIN, code);
                    return new Result(true, MessageConstant.SEND_VALIDATECODE_SUCCESS);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return new Result(true, MessageConstant.SEND_VALIDATECODE_FAIL);
    }

    @RequestMapping("/send4Order")
    public Result send4Order(String telephone) {
        //如果手机号为空
        if (telephone == null) {
            return new Result(false, MessageConstant.SEND_VALIDATECODE_FAIL);
        }
        //生成验证码
        String code = String.valueOf(ValidateCodeUtils.generateValidateCode(4));
        if (code == null && code.length() == 0) {
            return new Result(false, "无法生成验证码,请联系管理员");
        }
        try {
            //将验证码保存到redis数据库并发送到用户
            validateCodeService.sendValidateCodeShortMessage(telephone, RedisConst.SENDTYPE_ORDER, code);

            return new Result(true, MessageConstant.SEND_VALIDATECODE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.SEND_VALIDATECODE_FAIL);
        }
    }
}
