package com.itheima.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author : 光辉的mac
 * @ClassName ReportData
 * @date : 2020/9/9 16:21
 */
public class ReportData implements Serializable {
    /**
     *
     *    reportDate: null,
     *    todayNewMember: 0,
     *    totalMember: 0,
     *    thisWeekNewMember: 0,
     *    thisMonthNewMember: 0,
     *    todayOrderNumber: 0,
     *    todayVisitsNumber: 0,
     *    thisWeekOrderNumber: 0,
     *    thisWeekVisitsNumber: 0,
     *    thisMonthOrderNumber: 0,
     *    thisMonthVisitsNumber: 0,
     *    hotSetmeal: [
     *        {name: '阳光爸妈升级肿瘤12项筛查（男女单人）体检套餐', setmeal_count: 200, proportion: 0.222},
     *        {name: '阳光爸妈升级肿瘤12项筛查体检套餐', setmeal_count: 200, proportion: 0.222}
     *                 ],
     */
    //当前日期
    private String reportDate;
    //今天新增会员数
    private Long todayNewMember;
    //总会员数
    private Long totalMember;
    //本周新增会员数
    private Long thisWeekNewMember;
    //本月新增会员数
    private Long thisMonthNewMember;
    //今日预约数
    private Long todayOrderNumber;
    //今日到诊数
    private Long todayVisitsNumber;
    //本周预约数
    private Long thisWeekOrderNumber;
    //本周到诊数
    private Long thisWeekVisitsNumber;
    //本月预约数
    private Long thisMonthOrderNumber;
    //本月到诊数
    private Long thisMonthVisitsNumber;
    //人气最高的两个套餐 和对应的人数,占比
    private List<Map<String,Object>> hotSetmeal;

    public ReportData() {
    }

    public ReportData(String reportDate, Long todayNewMember, Long totalMember, Long thisWeekNewMember, Long thisMonthNewMember, Long todayOrderNumber, Long todayVisitsNumber, Long thisWeekOrderNumber, Long thisWeekVisitsNumber, Long thisMonthOrderNumber, Long thisMonthVisitsNumber, List<Map<String, Object>> hotSetmeal) {
        this.reportDate = reportDate;
        this.todayNewMember = todayNewMember;
        this.totalMember = totalMember;
        this.thisWeekNewMember = thisWeekNewMember;
        this.thisMonthNewMember = thisMonthNewMember;
        this.todayOrderNumber = todayOrderNumber;
        this.todayVisitsNumber = todayVisitsNumber;
        this.thisWeekOrderNumber = thisWeekOrderNumber;
        this.thisWeekVisitsNumber = thisWeekVisitsNumber;
        this.thisMonthOrderNumber = thisMonthOrderNumber;
        this.thisMonthVisitsNumber = thisMonthVisitsNumber;
        this.hotSetmeal = hotSetmeal;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public Long getTodayNewMember() {
        return todayNewMember;
    }

    public void setTodayNewMember(Long todayNewMember) {
        this.todayNewMember = todayNewMember;
    }

    public Long getTotalMember() {
        return totalMember;
    }

    public void setTotalMember(Long totalMember) {
        this.totalMember = totalMember;
    }

    public Long getThisWeekNewMember() {
        return thisWeekNewMember;
    }

    public void setThisWeekNewMember(Long thisWeekNewMember) {
        this.thisWeekNewMember = thisWeekNewMember;
    }

    public Long getThisMonthNewMember() {
        return thisMonthNewMember;
    }

    public void setThisMonthNewMember(Long thisMonthNewMember) {
        this.thisMonthNewMember = thisMonthNewMember;
    }

    public Long getTodayOrderNumber() {
        return todayOrderNumber;
    }

    public void setTodayOrderNumber(Long todayOrderNumber) {
        this.todayOrderNumber = todayOrderNumber;
    }

    public Long getTodayVisitsNumber() {
        return todayVisitsNumber;
    }

    public void setTodayVisitsNumber(Long todayVisitsNumber) {
        this.todayVisitsNumber = todayVisitsNumber;
    }

    public Long getThisWeekOrderNumber() {
        return thisWeekOrderNumber;
    }

    public void setThisWeekOrderNumber(Long thisWeekOrderNumber) {
        this.thisWeekOrderNumber = thisWeekOrderNumber;
    }

    public Long getThisWeekVisitsNumber() {
        return thisWeekVisitsNumber;
    }

    public void setThisWeekVisitsNumber(Long thisWeekVisitsNumber) {
        this.thisWeekVisitsNumber = thisWeekVisitsNumber;
    }

    public Long getThisMonthOrderNumber() {
        return thisMonthOrderNumber;
    }

    public void setThisMonthOrderNumber(Long thisMonthOrderNumber) {
        this.thisMonthOrderNumber = thisMonthOrderNumber;
    }

    public Long getThisMonthVisitsNumber() {
        return thisMonthVisitsNumber;
    }

    public void setThisMonthVisitsNumber(Long thisMonthVisitsNumber) {
        this.thisMonthVisitsNumber = thisMonthVisitsNumber;
    }

    public List<Map<String, Object>> getHotSetmeal() {
        return hotSetmeal;
    }

    public void setHotSetmeal(List<Map<String, Object>> hotSetmeal) {
        this.hotSetmeal = hotSetmeal;
    }
}
