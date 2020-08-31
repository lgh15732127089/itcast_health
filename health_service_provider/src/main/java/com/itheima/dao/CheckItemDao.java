package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckItem;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author : 光辉的mac
 * @ClassName CheckItemDao
 * @date : 2020/8/31 16:38
 */
public interface CheckItemDao {

    @Insert("INSERT INTO `t_checkitem` ( `code`, `name`, `sex`, `age`, `price`, `type`, `attention`, `remark`) VALUES ( #{code}, #{name}, #{sex}, #{age}, #{price}, #{type}, #{attention}, #{remark})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    void add(CheckItem checkitem);


    Page<CheckItem> findByCondition(@Param("value") String queryString);
}
