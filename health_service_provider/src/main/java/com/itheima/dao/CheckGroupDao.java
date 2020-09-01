package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.CheckItem;
import org.apache.ibatis.annotations.*;

import java.util.List;

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

    @Select("select count(0) from t_checkgroup_checkitem where checkgroup_id = #{id}")
    long findCheckItemByCheckGroupId(@Param("id") Integer id);

    @Delete("delete from t_checkgroup where id = #{id}")
    void delById(@Param("id") Integer id);

    @Select("select * from t_checkgroup where id=#{id}")
    CheckGroup findCheckGroupById(@Param("id")Integer id);

    @Select("select checkitem_id from t_checkgroup_checkitem where checkgroup_id = #{id}")
    List<Integer> findAllCheckItemIdsByCheckGroupId(@Param("id") Integer id);

    @Update("UPDATE `t_checkgroup` SET `code`=#{code}, `name`=#{name}, `helpCode`=#{helpCode}, `sex`=#{sex}, `remark`=#{remark}, `attention`=#{attention} WHERE (`id`= #{id})")
    void edit(CheckGroup checkGroup);

    @Delete("delete from t_checkgroup_checkitem where checkgroup_id = #{id}")
    void delRelation(@Param("id") Integer id);

    @Select("select * from t_checkgroup")
    List<CheckGroup> findAllCheckGroup();

}
