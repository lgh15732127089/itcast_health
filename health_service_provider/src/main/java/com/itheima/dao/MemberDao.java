package com.itheima.dao;

import com.itheima.pojo.Member;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.Date;

/**
 * @author : 光辉的mac
 * @ClassName MemberDao
 * @date : 2020/9/5 16:42
 */
public interface MemberDao {

    @Select("select * from t_member where phoneNumber = #{telephone}")
    Member findByTelephone(String telephone);

    @Insert("INSERT INTO `t_member` ( `name`, `sex`, `idCard`, `phoneNumber`, `regTime`) VALUES (  #{name}, #{sex}, #{idCard}, #{phoneNumber}, #{regTime})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    void addMember(Member member);

    @Select("select count(0) from t_member where regTime <= #{lastDay4Month}")
    Long findCountByLastDay(Date lastDay4Month);
}
