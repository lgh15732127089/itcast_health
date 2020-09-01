package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.customExceptions.CustomException;
import com.itheima.dao.CheckGroupDao;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.CheckItem;
import com.itheima.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
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

    @Override
    public void delById(Integer id) {
        long count = checkGroupDao.findCheckItemByCheckGroupId(id);
        if (count>0){
            throw new CustomException("该检查组中有检查项不能删除!");
        }
        checkGroupDao.delById(id);
    }

    @Override
    public CheckGroup findAllById(Integer id) {
        CheckGroup checkGroup = checkGroupDao.findCheckGroupById(id);
        return checkGroup;
    }

    @Override
    public List<Integer> findCheckItemIdsById(Integer id) {
        List<Integer> ids = checkGroupDao.findAllCheckItemIdsByCheckGroupId(id);
        return ids;
    }

    @Override
    public void edit(List checkitems, CheckGroup checkGroup) {
        //1.修改检查组
        checkGroupDao.edit(checkGroup);
        //2.1 先删除该检查组原来的关系
        checkGroupDao.delRelation(checkGroup.getId());
        //2.2 添加新的关系
        if(checkGroup.getId() != null && checkitems != null && checkitems.size() > 0){
            for (Object checkitemId : checkitems) {
                Integer id = Integer.parseInt(String.valueOf(checkitemId));
                checkGroupDao.addRelation(checkGroup.getId(),id);
            }
        }
    }
}
