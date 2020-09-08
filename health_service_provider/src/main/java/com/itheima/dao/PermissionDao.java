package com.itheima.dao;

import com.itheima.pojo.Permission;
import org.apache.ibatis.annotations.Select;

/**
 * @author : 光辉的mac
 * @ClassName PermissionDao
 * @date : 2020/9/8 17:12
 */
public interface PermissionDao {

    @Select("select * from t_permission where id in (select permission_id from t_role_permission where role_id = #{id})")
    Permission findPermissionByRoleId(Integer id);
}
