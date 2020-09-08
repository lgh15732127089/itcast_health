package com.itheima.dao;

import com.itheima.pojo.Role;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;


/**
 * @author : 光辉的mac
 * @ClassName RoleDao
 * @date : 2020/9/8 17:04
 */
public interface RoleDao {

    @Results({
            @Result(column = "id",id = true,property = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "keyword",property = "keyword"),
            @Result(column = "id",property = "permissions",many = @Many(select = "com.itheima.dao.PermissionDao.findPermissionByRoleId")),
    })
    @Select("select * from t_role where id in(select role_id from t_user_role where user_id = #{id})")
    Role findRoleByUserId(Integer id);
}
