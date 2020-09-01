package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.common.MessageConst;
import com.itheima.common.MessageConstant;
import com.itheima.common.RedisConst;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.Setmeal;
import com.itheima.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import java.util.List;

/**
 * @author : 光辉的mac
 * @ClassName SetmealController
 * @date : 2020/9/1 20:00
 */
@RestController
@RequestMapping("/setmeal")
@Slf4j
public class SetmealController {

    @Reference
    private SetmealService setmealService;

    @Autowired
    private JedisPool jedisPool;

    @RequestMapping("/findAllCheckGroup")
    public Result findAllCheckGroup(){
        List<CheckGroup> checkGroupList = setmealService.findAllCheckGroup();
        return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS,checkGroupList);
    }

    @RequestMapping("/add")
    public Result add(@RequestParam("checkgroupIds") List checkgroupIds, @RequestBody Setmeal setmeal){
        setmealService.add(checkgroupIds,setmeal);
        return new Result(true,MessageConstant.ADD_SETMEAL_SUCCESS);
    }

    @RequestMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult = setmealService.findPage(queryPageBean);
        return new Result(true,MessageConstant.QUERY_SETMEAL_SUCCESS,pageResult);
    }

    @RequestMapping("/addImgName")
    public Result addImgName(@RequestParam("imgName") String imgName){
        log.debug("SetmealControlller:addImgName:" + imgName);
        jedisPool.getResource().sadd(RedisConst.SETMEAL_PIC_RESOURCES, imgName);
        log.debug("图片名称添加到redis成功！！");
        return new Result(true, "图片名称添加到redis成功!");
    }
}
