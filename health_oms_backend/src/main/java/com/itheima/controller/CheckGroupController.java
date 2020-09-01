package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.common.MessageConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.CheckGroup;
import com.itheima.service.CheckGroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : 光辉的mac
 * @ClassName CheckGroupController
 * @date : 2020/8/31 19:40
 */
@RequestMapping("/checkgroup")
@RestController
@Slf4j
public class CheckGroupController {

    @Reference
    private CheckGroupService checkGroupService;

    @RequestMapping("/add")
    public Result add(@RequestParam("checkitems") List checkitems, @RequestBody CheckGroup checkGroup) {
        log.debug("输入的检查组为 " + checkitems + checkGroup);
        checkGroupService.add(checkitems,checkGroup);
        return new Result(true, MessageConstant.ADD_CHECKGROUP_SUCCESS);
    }

    @RequestMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){
        log.debug("查询检查组的条件为 "+queryPageBean);
        PageResult pageResult = checkGroupService.findPage(queryPageBean);
        return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,pageResult);
    }

    @RequestMapping("/delById")
    public Result delById(@RequestParam("id") Integer id){
        log.debug("要删除的检查组id为 "+id);
        checkGroupService.delById(id);
        return new Result(true,MessageConstant.DELETE_CHECKITEM_SUCCESS);
    }

    @RequestMapping("/findAllById")
    public Result findAllById(@RequestParam("id") Integer id){
        log.debug("要查询的检查组id为"+id);
        CheckGroup checkGroup = checkGroupService.findAllById(id);
        return new Result(true,MessageConstant.QUERY_CHECKGROUP_SUCCESS,checkGroup);
    }

    @RequestMapping("/edit")
    public Result edit(@RequestParam("checkitems") List checkitems, @RequestBody CheckGroup checkGroup) {
        log.debug("输入的检查组为 " + checkitems + checkGroup);
        checkGroupService.edit(checkitems,checkGroup);
        return new Result(true, MessageConstant.ADD_CHECKGROUP_SUCCESS);
    }

    @RequestMapping("/findCheckItemIdsById")
    public Result findCheckItemIdsById(@RequestParam("id") Integer id){
        log.debug("输入的检查组id为"+id);
        List<Integer> ids = checkGroupService.findCheckItemIdsById(id);
        return new Result(true,MessageConstant.QUERY_CHECKGROUP_SUCCESS,ids);
    }
}
