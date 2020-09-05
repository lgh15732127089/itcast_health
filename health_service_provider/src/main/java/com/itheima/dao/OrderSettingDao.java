package com.itheima.dao;

import com.itheima.pojo.OrderSetting;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

/**
 * @author : 光辉的mac
 * @ClassName OrderSettingDao
 * @date : 2020/9/2 17:03
 */
public interface OrderSettingDao {
    @Select("select * from t_ordersetting where orderDate=#{orderDate}")
    OrderSetting findByOrderSettingDate(Date orderDate);

    @Update("UPDATE `t_ordersetting` SET `number`=#{number} WHERE (`id`='#{id})")
    void updateOrderSetting(OrderSetting newOrderSetting);

    @Insert("INSERT INTO `t_ordersetting` ( `orderDate`, `number`, `reservations`) VALUES ( #{orderDate}, #{number}, '0')")
    void addOrderSetting(OrderSetting newOrderSetting);

    @Select("select * from t_ordersetting where orderDate between #{param1} and #{param2}")
    List<OrderSetting> findOrderSettingByMonth(String thisMonthFirstDay, String thisMonthLastDay);

    @Update("update t_ordersetting set reservations=#{reservations} where orderDate=#{orderDate}")
    void editOrderSettingByReservations(@Param("orderDate") Date orderDate,@Param("reservations") Integer reservations);

}