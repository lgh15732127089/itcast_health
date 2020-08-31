package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.dao.CheckGroupDao;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.CheckItem;
import com.itheima.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author : 光辉的mac
 * @ClassName CheckGroupServiceImpl
 * @date : 2020/8/31 20:03
 */
@Service
public class CheckGroupServiceImpl implements CheckGroupService {

    @Autowired
    private CheckGroupDao checkGroupDao;

    @Override
    public void add(List checkitemIds, CheckGroup checkGroup) {
        checkGroupDao.addCheckGroup(checkGroup);
        if (checkitemIds != null && checkitemIds.size()>0){
            for (int i = 0; i < checkitemIds.size(); i++) {
                Integer id = Integer.parseInt(String.valueOf(checkitemIds.get(i)));
                checkGroupDao.addRelation(checkGroup.getId(),id);
            }
        }
    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        Page<CheckGroup> checkGroups = checkGroupDao.findByCondition(queryPageBean.getQueryString());
        return new PageResult(checkGroups.getTotal(),checkGroups);
    }
}
