package com.itheima.controller;

import com.itheima.entity.Result;
import com.qiniu.util.Auth;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : 光辉的mac
 * @ClassName TokenController
 * @date : 2020/9/1 18:40
 */
@RestController
public class TokenController {

    @RequestMapping("/getToken")
    public Result getToken(){
        String accessKey = "ZKr0GoVEFGOAabwUJclF7TKIVaLUXiVtGNpfHDDn";
        String secretKey = "m7iUwu0PK63mgDt7Skq3NlHa4TGD4-1cbOerfyxY";
        String bucket = "guanghui31";
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        System.out.println(upToken);
        return new Result(true,"查询token成功",upToken);
    }
}
