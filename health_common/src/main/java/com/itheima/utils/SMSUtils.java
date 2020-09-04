package com.itheima.utils;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

/**
 * @author : 光辉的mac
 * @ClassName SMSUtils
 * @date : 2020/9/4 19:05
 */
public class SMSUtils {
    private static String AccessKeyId="LTAI4GJuPXsdYhMQgY9vAK3o";
    private static String Secret="tHQNW0YqT5bDUPpPLSNBm0YIWUxgMv";
    /*
     * @Author lgh
     * @Date 19:06 2020/9/4
     * @Param [telephone, type, code] 第一个参数是电话,第二个参数是类型,第三个参数是验证码
     * @return void
     **/
    public static void sendShortMessage(String telephone,String code){
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", AccessKeyId, Secret);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", telephone);
        request.putQueryParameter("SignName", "在九月");
        request.putQueryParameter("TemplateCode", "SMS_176450181");

        if(code !=null || code.length() > 0){
            request.putQueryParameter("TemplateParam", "{\"code\":\""+code+"\"}");
        }

        try {
            CommonResponse response = client.getCommonResponse(request);

            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }


    }
}
