package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckGroup;

import java.util.List;

/**
 * @author : 光辉的mac
 * @ClassName CheckGroupService
 * @date : 2020/8/31 20:02
 */
public interface CheckGroupService {
    void add(List checkitemIds, CheckGroup checkGroup);

    PageResult findPage(QueryPageBean queryPageBean);
}
