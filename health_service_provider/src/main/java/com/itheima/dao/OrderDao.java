package com.itheima.dao;

import com.itheima.pojo.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * @author : 光辉的mac
 * @ClassName OrderDao
 * @date : 2020/9/5 16:42
 */
public interface OrderDao {

    @Select("select m.name member,s.name setmeal,o.orderDate,o.orderType from t_order o,t_member m,t_setmeal s where o.id=#{id} and o.member_id=m.id and s.id=o.setmeal_id")
    Map findByOrderId(Integer id);

    @Insert("INSERT INTO `t_order` ( `member_id`, `orderDate`, `orderType`, `orderStatus`, `setmeal_id`) VALUES ( #{memberId}, #{orderDate}, #{orderType}, #{orderStatus}, #{setmealId})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    void addOrder(Order order);

    @Select("select count(0) from t_order where member_id = #{memberId} and orderDate = #{orderDate} and setmeal_id =#{setmealId}")
    Long findCountByOrder(Order order);
}
