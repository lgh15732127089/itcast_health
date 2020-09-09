package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.dao.ReportDao;
import com.itheima.pojo.ReportData;
import com.itheima.service.ReportService;
import com.itheima.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author : 光辉的mac
 * @ClassName ReportServiceImpl
 * @date : 2020/9/9 17:12
 */
@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportDao reportDao;

    @Override
    public ReportData getBusinessReportData() {
        //需要传入的参数有 当前时间 本周第一天 本周最后一天 本月第一天 本月最后一天
        //需要传入字符串
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        //今天
        String thisDayDate = simpleDateFormat.format(date);
        //本周一
        String thisWeekMonday = simpleDateFormat.format(DateUtils.getThisWeekMonday());
        //本周最后一天
        String thisWeekSunday = simpleDateFormat.format(DateUtils.getSundayOfThisWeek());
        //本月第一天
        String thisMonthFirstDay = simpleDateFormat.format(DateUtils.getFirstDay4ThisMonth());
        //本月最后一天
        String thisMonthLastDay = simpleDateFormat.format(DateUtils.getLastDay4ThisMonth());

        ReportData reportData = reportDao.getBusinessReportData(thisDayDate,thisWeekMonday,thisWeekSunday,thisMonthFirstDay,thisMonthLastDay);

        List<Map<String,Object>> list = reportDao.getHotSetmeal();
        reportData.setReportDate(simpleDateFormat.format(new Date()));
        reportData.setHotSetmeal(list);

        return reportData;
    }
}
