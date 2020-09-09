package com.itheima.dao;

import com.itheima.pojo.ReportData;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author : 光辉的mac
 * @ClassName ReportDao
 * @date : 2020/9/9 17:33
 */
public interface ReportDao {

    @Select("select s.name name,count(o.setmeal_id) setmeal_count,count(o.setmeal_id)/(select count(0) from t_order) proportion from t_setmeal s LEFT JOIN t_order o on s.id = o.setmeal_id group by s.id ORDER BY count(o.setmeal_id) desc LIMIT 2")
    List<Map<String, Object>> getHotSetmeal();

    @Select("SELECT " +
            "count(0) totalMember, " +
            "(SELECT count(0) FROM t_member WHERE regTime = #{thisDayDate}) todayNewMember," +
            "(SELECT count(0) FROM t_member WHERE regTime >= #{thisWeekMonday}) thisWeekNewMember," +
            "(SELECT count(0) FROM t_member WHERE regTime >= #{thisMonthFirstDay}) thisMonthNewMember," +
            "(select count(0) from t_order where orderDate = #{thisDayDate}) todayOrderNumber," +
            "(select count(0) from t_order where orderDate = #{thisDayDate} and orderStatus='已到诊') todayVisitsNumber," +
            "(select count(0) from t_order WHERE orderDate BETWEEN #{thisWeekMonday} and #{thisWeekSunday}) thisWeekOrderNumber," +
            "(select count(0) from t_order WHERE orderDate BETWEEN #{thisWeekMonday} and #{thisWeekSunday} and orderStatus = '已到诊') thisWeekVisitsNumber," +
            "(select count(0) from t_order WHERE orderDate BETWEEN #{thisMonthFirstDay} and #{thisMonthLastDay}) thisMonthOrderNumber," +
            "(select count(0) from t_order WHERE orderDate BETWEEN #{thisMonthFirstDay} and #{thisMonthLastDay} and orderStatus = '已到诊') thisMonthVisitsNumber " +
            "FROM " +
            "t_member")
    ReportData getBusinessReportData(@Param("thisDayDate") String thisDayDate,@Param("thisWeekMonday") String thisWeekMonday,@Param("thisWeekSunday") String thisWeekSunday,@Param("thisMonthFirstDay") String thisMonthFirstDay,@Param("thisMonthLastDay") String thisMonthLastDay);

}
