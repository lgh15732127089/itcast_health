package com.itheima.dao;

import com.itheima.pojo.SysUser;
import org.apache.ibatis.annotations.*;

/**
 * @author : 光辉的mac
 * @ClassName UserDao
 * @date : 2020/8/28 11:08
 */
@Mapper
public interface UserDao {

    @Results({
            @Result(column = "id",property = "id",id = true),
            @Result(column = "username",property = "username"),
            @Result(column = "password",property = "password"),
            @Result(column = "id",property = "roles",many = @Many(select = "com.itheima.dao.RoleDao.findRoleByUserId")),
    })
    @Select("select * from t_user where username = #{username}")
    SysUser findUserByUsername(@Param("username") String username);
}
