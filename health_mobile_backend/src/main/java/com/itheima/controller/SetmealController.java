package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.common.MessageConstant;
import com.itheima.entity.Result;
import com.itheima.pojo.Setmeal;
import com.itheima.service.SetmealService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : 光辉的mac
 * @ClassName SetmealController
 * @date : 2020/9/4 16:15
 */
@RestController
@RequestMapping("/setmeal")
public class SetmealController {

    @Reference
    private SetmealService setmealService;

    @RequestMapping("/findAllSetmeal")
    public Result findAllSetmeal(){
        List<Setmeal> setmealList = setmealService.findAllSetmeal();
        return new Result(true, MessageConstant.QUERY_SETMEALLIST_SUCCESS,setmealList);
    }

    @RequestMapping("/findById")
    public Result findById(Integer id){
        Setmeal setmeal = setmealService.findById(id);
        return new Result(true,MessageConstant.QUERY_SETMEAL_SUCCESS,setmeal);
    }

    @RequestMapping("/findSetmealById")
    public  Result findSetmealById(Integer id){
        Setmeal setmeal = setmealService.findSetmealById(id);
        return new Result(true,MessageConstant.QUERY_SETMEAL_SUCCESS,setmeal);
    }
}
