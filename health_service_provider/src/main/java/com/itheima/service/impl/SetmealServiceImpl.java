package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.common.RedisConst;
import com.itheima.dao.CheckGroupDao;
import com.itheima.dao.SetmealDao;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.Setmeal;
import com.itheima.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisPool;

import java.util.List;

/**
 * @author : 光辉的mac
 * @ClassName SetmealServiceImpl
 * @date : 2020/9/1 20:02
 */
@Service
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private CheckGroupDao checkGroupDao;

    @Autowired
    private SetmealDao setmealDao;

    @Autowired
    private JedisPool jedisPool;

    @Override
    public List<CheckGroup> findAllCheckGroup() {
        return checkGroupDao.findAllCheckGroup();
    }

    @Override
    public void add(List checkgroupIds, Setmeal setmeal) {
        setmealDao.addSetmeal(setmeal);
        if (checkgroupIds != null && checkgroupIds.size() > 0) {
            for (Object checkgroupId : checkgroupIds) {
                Integer id = Integer.parseInt(String.valueOf(checkgroupId));
                setmealDao.addRelation(setmeal.getId(), id);
            }
        }
        jedisPool.getResource().srem(RedisConst.SETMEAL_PIC_RESOURCES, setmeal.getImg());
    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        Page<Setmeal> setmeals = setmealDao.findByCondition(queryPageBean.getQueryString());
        return new PageResult(setmeals.getTotal(), setmeals);
    }
}
