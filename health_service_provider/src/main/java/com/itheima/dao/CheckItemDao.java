package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckItem;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author : 光辉的mac
 * @ClassName CheckItemDao
 * @date : 2020/8/31 16:38
 */
public interface CheckItemDao {

    @Insert("INSERT INTO `t_checkitem` ( `code`, `name`, `sex`, `age`, `price`, `type`, `attention`, `remark`) VALUES ( #{code}, #{name}, #{sex}, #{age}, #{price}, #{type}, #{attention}, #{remark})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    void add(CheckItem checkitem);

    @Select("<script> "
            + " select * from t_checkitem "
            + "<where>"
            + " <if test = 'value != null'> "  //if标签开始
            + "  code like concat('%',#{value},'%') or name like concat('%',#{value},'%') "
            + " </if> "  //if标签结束
            + "</where>"
            + "</script>")
    Page<CheckItem> findByCondition(@Param("value") String queryString);

    @Delete("delete from t_checkgroup_checkitem where checkitem_id = #{id}")
    void delIdForCheckGroup(@Param("id") Integer id);

    @Delete("delete from t_checkitem where id = #{id}")
    void delIdForCheckItem(@Param("id")Integer id);

    @Select("select * from t_checkitem where id =#{id}")
    CheckItem findById(@Param("id")Integer id);

    @Update("UPDATE t_checkitem set name = #{name},sex = #{sex},code = #{code},age = #{age},price = #{price},type = #{type},attention = #{attention},remark = #{remark} where id = #{id}")
    void edit(CheckItem checkitem);

    @Select("select * from t_checkitem")
    List<CheckItem> selectAllCheckItem();

    @Select("select * from t_checkitem where id in (select checkitem_id from t_checkgroup_checkitem where checkgroup_id = #{id})")
    List<CheckItem> findCheckItemByCheckGroupId();
}
