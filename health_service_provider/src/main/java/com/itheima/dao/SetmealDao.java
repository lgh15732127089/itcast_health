package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.Setmeal;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

/**
 * @author : 光辉的mac
 * @ClassName SetmealDao
 * @date : 2020/9/1 20:16
 */
public interface SetmealDao {

    @Insert("INSERT INTO `t_setmeal` ( `name`, `code`, `helpCode`, `sex`, `age`, `price`, `remark`, `attention`, `img`) VALUES (#{name}, #{code}, #{helpCode}, #{sex}, #{age}, #{price}, #{remark}, #{attention}, #{img})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    void addSetmeal(Setmeal setmeal);

    @Insert("INSERT INTO `itcast_health`.`t_setmeal_checkgroup` (`setmeal_id`, `checkgroup_id`) VALUES (#{setmealid}, #{checkgroupid})")
    void addRelation(@Param("setmealid") Integer setmealid,@Param("checkgroupid") Integer checkgroupid);

    Page<Setmeal> findByCondition(String queryString);
}
