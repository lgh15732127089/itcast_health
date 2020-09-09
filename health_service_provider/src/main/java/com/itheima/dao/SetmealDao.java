package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.Setmeal;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @author : 光辉的mac
 * @ClassName SetmealDao
 * @date : 2020/9/1 20:16
 */
public interface SetmealDao {

    @Insert("INSERT INTO `t_setmeal` ( `name`, `code`, `helpCode`, `sex`, `age`, `price`, `remark`, `attention`, `img`) VALUES (#{name}, #{code}, #{helpCode}, #{sex}, #{age}, #{price}, #{remark}, #{attention}, #{img})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    void addSetmeal(Setmeal setmeal);

    @Insert("INSERT INTO `t_setmeal_checkgroup` (`setmeal_id`, `checkgroup_id`) VALUES (#{setmealid}, #{checkgroupid})")
    void addRelation(@Param("setmealid") Integer setmealid,@Param("checkgroupid") Integer checkgroupid);

    @Select("<script> "
            + " select * from t_setmeal "
            + "<where>"
            + " <if test = 'value != null'> "  //if标签开始
            + "  code like concat('%',#{value},'%') or helpCode like concat('%',#{value},'%') or name like concat('%',#{value},'%') "
            + " </if> "  //if标签结束
            + "</where>"
            + "</script>")
    Page<Setmeal> findByCondition(@Param("value") String queryString);

    @Select("select * from t_setmeal")
    List<Setmeal> findAllSetmeal();

    @Select("select * from t_setmeal where id = #{id}")
    @Results({
                    @Result(id = true,column = "id",property = "id"),
                    @Result(column = "name",property = "name"),
                    @Result(column = "code",property = "code"),
                    @Result(column = "helpCode",property = "helpCode"),
                    @Result(column = "sex",property = "sex"),
                    @Result(column = "age",property = "age"),
                    @Result(column = "price",property = "price"),
                    @Result(column = "remark",property = "remark"),
                    @Result(column = "attention",property = "attention"),
                    @Result(column = "img",property = "img"),
                    @Result(column = "id",property = "checkGroups",many = @Many(select = "com.itheima.dao.CheckGroupDao.findCheckGroupBySetmealId")),
    })
    Setmeal findById(Integer id);

    @Select("select * from t_setmeal where id = #{id}")
    Setmeal findSetmealById(Integer id);

    @Select("select s.name name,count(o.setmeal_id) value from t_setmeal s LEFT JOIN t_order o on s.id = o.setmeal_id GROUP BY s.id")
    List<Map<String, String>> findSetmealAndOrder();
}
