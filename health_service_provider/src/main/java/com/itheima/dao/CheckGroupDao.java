package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckGroup;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

/**
 * @author : 光辉的mac
 * @ClassName CheckGroupDao
 * @date : 2020/8/31 20:09
 */
public interface CheckGroupDao {

    @Insert("INSERT INTO `t_checkgroup` ( `code`, `name`, `helpCode`, `sex`, `remark`, `attention`) VALUES (#{code}, #{name}, #{helpCode}, #{sex}, #{remark}, #{attention})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    void addCheckGroup(CheckGroup checkGroup);


    @Insert("INSERT INTO `t_checkgroup_checkitem` (`checkgroup_id`, `checkitem_id`) VALUES (#{checkgroupid}, #{checkitemid})")
    void addRelation(@Param("checkgroupid") Integer checkgroupid,@Param("checkitemid") Integer checkitemid);


    Page<CheckGroup> findByCondition(String queryString);
}
