package com.itheima.job;

import com.itheima.common.RedisConst;
import com.qiniu.common.QiniuException;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisPool;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

/**
 * @author : 光辉的mac
 * @ClassName ClearImgJob
 * @date : 2020/9/1 21:08
 */
public class ClearImgJob {

    @Autowired
    JedisPool jedisPool;

    /**
     * 步骤：
     *  1. 获取所有的图片名称
     *  2. 获取昨天的日期字符串
     *  3.开始删除
     *      3.1 删除昨天的图片
     *      3.2 删除redis中的图片名称
     */
    public void clear(){
        //获取所有的图片名称
        Set<String> imgNames  = jedisPool.getResource().smembers(RedisConst.SETMEAL_PIC_RESOURCES);
        //获取昨天的日期字符串
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -1);
        Date yesterday = cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String str = sdf.format(yesterday);
        //开始删除
        for (String imgName : imgNames) {
            //如果以昨天的日期开头则可以删除
            if(imgName != null && imgName.startsWith(str)){
                //删除昨天的图片
                delQiniu(imgName);
                //删除redis中的图片名称
                jedisPool.getResource().srem(RedisConst.SETMEAL_PIC_RESOURCES, imgName);
                System.out.println("删除了" + imgName + "图片!!");
            }
        }
    }

    /**
     * 删除七牛图片的方法
     */
    public void delQiniu(String imgName){
       //构造一个带指定Zone对象的配置类
       //Configuration cfg = new Configuration(Zone.zone0());
       //String key = imgName;
       //Auth auth = Auth.create("ZKr0GoVEFGOAabwUJclF7TKIVaLUXiVtGNpfHDDn", "m7iUwu0PK63mgDt7Skq3NlHa4TGD4-1cbOerfyxY");
       //BucketManager bucketManager = new BucketManager(auth, cfg);
       //try {
       //    bucketManager.delete("guanghui31", key);
       //} catch (QiniuException ex) {
       //    //如果遇到异常，说明删除失败
       //    System.err.println(ex.code());
       //    System.err.println(ex.response.toString());
       //}

        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.region0());
        //...其他参数参考类注释
        String accessKey = "ZKr0GoVEFGOAabwUJclF7TKIVaLUXiVtGNpfHDDn";
        String secretKey = "m7iUwu0PK63mgDt7Skq3NlHa4TGD4-1cbOerfyxY";
        String bucket = "guanghui31";
        String key = imgName;
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(bucket, key);
        } catch (QiniuException ex) {
            //如果遇到异常，说明删除失败
            System.err.println(ex.code());
            System.err.println(ex.response.toString());
        }

    }

}
