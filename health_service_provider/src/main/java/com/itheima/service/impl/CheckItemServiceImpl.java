package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.customExceptions.CustomException;
import com.itheima.dao.CheckItemDao;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckItem;
import com.itheima.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author : 光辉的mac
 * @ClassName CheckItemServiceImpl
 * @date : 2020/8/31 16:37
 */
@Service
public class CheckItemServiceImpl implements CheckItemService  {

    @Autowired
    private CheckItemDao checkItemDao;

    @Override
    public void add(CheckItem checkitem) {
        checkItemDao.add(checkitem);
    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        //开启分页
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        //获取查询结果
        Page<CheckItem> checkItemPage = checkItemDao.findByCondition(queryPageBean.getQueryString());
        return new PageResult(checkItemPage.getTotal(),checkItemPage);
    }

    @Override
    public void delById(Integer id) {
        checkItemDao.delIdForCheckGroup(id);
        checkItemDao.delIdForCheckItem(id);
    }

    @Override
    public CheckItem findById(Integer id) {
        return checkItemDao.findById(id);
    }

    @Override
    public void edit(CheckItem checkitem) {
        checkItemDao.edit(checkitem);
    }
}
