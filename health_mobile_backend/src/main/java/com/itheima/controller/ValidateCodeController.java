package com.itheima.controller;

import com.itheima.common.MessageConstant;
import com.itheima.entity.Result;
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

    @Autowired
    private JedisPool jedisPool;

    @RequestMapping("/send4Login")
    public Result send4Login(String telephone){
        if (telephone != null && telephone.length()>0) {

            //获取验证码
            String code = ValidateCodeUtils.generateValidateCode4String(4);
            if (code != null && code.length()>0){
                try {
                    //将登录验证码发送给用户
                    SMSUtils.sendShortMessage(RedisMessageConstant.SENDTYPE_LOGIN+"-"+telephone, code);
                    //获取redis对象
                    Jedis jedis = jedisPool.getResource();
                    //存储一个带有时间的验证码
                    jedis.setex(RedisMessageConstant.SENDTYPE_LOGIN+"-"+telephone,5*60,code);

                    return new Result(true, MessageConstant.SEND_VALIDATECODE_SUCCESS);
                } catch (Exception e) {
                    e.printStackTrace();
                    return new Result(true, MessageConstant.SEND_VALIDATECODE_FAIL);
                }
            }
        }
        return new Result(true, MessageConstant.SEND_VALIDATECODE_FAIL);
    }
}
