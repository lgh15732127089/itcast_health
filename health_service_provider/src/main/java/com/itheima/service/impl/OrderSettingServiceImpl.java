package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.customExceptions.CustomException;
import com.itheima.dao.OrderSettingDao;
import com.itheima.pojo.OrderSetting;
import com.itheima.service.OrderSettingService;
import com.itheima.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author : 光辉的mac
 * @ClassName OrderSettingServiceImpl
 * @date : 2020/9/2 17:02
 */
@Service
public class OrderSettingServiceImpl implements OrderSettingService {

    @Autowired
    private OrderSettingDao orderSettingDao;

    @Override
    public void importAdd(List<OrderSetting> orderSettingList) {
        if (orderSettingList != null && orderSettingList.size()>0) {
            for (OrderSetting orderSetting : orderSettingList) {
                editOrderSetting(orderSetting);
            }
        }
    }

    @Override
    public List<OrderSetting> findAllOrderSettingByMonth(String month) {
        //当前月第一天
        String thisMonthFirstDay = month + "-01";
        //当前月最后一天
        String thisMonthLastDay =new SimpleDateFormat("yyyy-MM-dd").format(DateUtils.getLastDay4Month(month));
        return orderSettingDao.findOrderSettingByMonth(thisMonthFirstDay,thisMonthLastDay);
    }

    @Override
    public void editOrderSetting(OrderSetting orderSetting) {
        OrderSetting newOrderSetting = orderSettingDao.findByOrderSettingDate(orderSetting.getOrderDate());
        if (newOrderSetting != null) {
            if (newOrderSetting.getReservations()>orderSetting.getNumber()){
                throw new CustomException("已预约人数大于可预约人数!");
            }else{
                orderSettingDao.updateOrderSetting(newOrderSetting);
            }
        } else {
            orderSettingDao.addOrderSetting(orderSetting);
        }
    }
}
