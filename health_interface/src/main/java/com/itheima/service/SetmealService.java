package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.Setmeal;

import java.util.List;

/**
 * @author : 光辉的mac
 * @ClassName SetmealService
 * @date : 2020/9/1 20:02
 */
public interface SetmealService {

    List<CheckGroup> findAllCheckGroup();

    void add(List checkgroupIds, Setmeal setmeal);

    PageResult findPage(QueryPageBean queryPageBean);

    List<Setmeal> findAllSetmeal();

    Setmeal findById(Integer id);

    Setmeal findSetmealById(Integer id);

}
