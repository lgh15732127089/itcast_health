package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.Page;
import com.itheima.common.MessageConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.CheckItem;
import com.itheima.service.CheckItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : 光辉的mac
 * @ClassName CheckitemController
 * @date : 2020/8/31 16:29
 */
@RestController
@RequestMapping("/checkitem")
@Slf4j
public class CheckitemController {
    @Reference
    private CheckItemService checkItemService;

    @PreAuthorize("hasAuthority('CHECKITEM_ADD')")
    @RequestMapping("/add")
    public Result add(@RequestBody CheckItem checkitem){
        log.debug("输入的检查组为 "+checkitem);
        checkItemService.add(checkitem);
        return new Result(true, MessageConstant.ADD_CHECKGROUP_SUCCESS);
    }

    @RequestMapping("/findPage")
    @PreAuthorize("hasAuthority('CHECKITEM_QUERY')")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){
        log.debug("查询检查项的条件为 "+queryPageBean);
        PageResult pageResult = checkItemService.findPage(queryPageBean);
        return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,pageResult);
    }

    @RequestMapping("/delById")
    @PreAuthorize("hasAuthority('CHECKITEM_DELETE')")
    public Result delById(@RequestParam("id") Integer id){
        log.debug("要删除的检查项id为 "+id);
        checkItemService.delById(id);
        return new Result(true,MessageConstant.DELETE_CHECKITEM_SUCCESS);
    }

    @RequestMapping("/findById")
    @PreAuthorize("hasAuthority('CHECKITEM_QUERY')")
    public Result findById(@RequestParam("id") Integer id){
        log.debug("要查找的检查项id为 "+id);
        CheckItem checkItem = checkItemService.findById(id);
        return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,checkItem);
    }

    @RequestMapping("/edit")
    @PreAuthorize("hasAuthority('CHECKITEM_EDIT')")
    public Result edit(@RequestBody CheckItem checkitem){
        log.debug("要修改的检查组为 "+checkitem);
        checkItemService.edit(checkitem);
        return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS);
    }


    @RequestMapping("/selectAllCheckItem")
    @PreAuthorize("hasAuthority('CHECKITEM_QUERY')")
    public Result selectAllCheckItem(){
        List<CheckItem> checkItems = checkItemService.selectAllCheckItem();
        log.debug("所有的检查组为 "+checkItems);
        return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,checkItems);
    }
}
