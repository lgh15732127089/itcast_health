package com.itheima.service;

import com.itheima.pojo.OrderSetting;

import java.util.List;

/**
 * @author : 光辉的mac
 * @ClassName OrderSettingService
 * @date : 2020/9/2 17:00
 */
public interface OrderSettingService {

    void importAdd(List<OrderSetting> orderSettingList);

    List<OrderSetting> findAllOrderSettingByMonth(String month);

    void editOrderSetting(OrderSetting orderSetting);
}
