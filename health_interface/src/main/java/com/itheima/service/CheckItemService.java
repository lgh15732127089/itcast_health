package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckItem;

/**
 * @author : 光辉的mac
 * @ClassName CheckItemService
 * @date : 2020/8/31 16:36
 */
public interface CheckItemService {
    void add(CheckItem checkitem);

    PageResult findPage(QueryPageBean queryPageBean);

    void delById(Integer id);

    CheckItem findById(Integer id);

    void edit(CheckItem checkitem);
}
