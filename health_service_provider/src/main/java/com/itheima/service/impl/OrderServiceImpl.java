package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.common.MessageConstant;
import com.itheima.dao.MemberDao;
import com.itheima.dao.OrderDao;
import com.itheima.dao.OrderSettingDao;
import com.itheima.entity.Result;
import com.itheima.pojo.Member;
import com.itheima.pojo.Order;
import com.itheima.pojo.OrderSetting;
import com.itheima.service.OrderService;
import com.itheima.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Map;

/**
 * @author : 光辉的mac
 * @ClassName OrderServiceImpl
 * @date : 2020/9/5 15:57
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderSettingDao orderSettingDao;

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private OrderDao orderDao;

    @Override
    public Result addOrderByCondition(Map<String, String> map) {
        //获取参数信息
        //名字
        String name = map.get("name");
        //性别
        String sex = map.get("sex");
        //手机号
        String telephone = map.get("telephone");
        //验证码
        String validateCode = map.get("validateCode");
        //身份证
        String idCard = map.get("idCard");
        //体检日期
        Date orderDate = DateUtils.parseString2Date(map.get("orderDate"));
        //套餐id
        Integer setmealId = Integer.parseInt(map.get("setmealId"));
        //查询日期是否可以体检
        OrderSetting orderSetting = orderSettingDao.findByOrderSettingDate(orderDate);
        if (orderSetting == null) {
            return new Result(false, "当前日期不能体检!");
        }
        if (orderSetting.getReservations() >= orderSetting.getNumber()) {
            return new Result(false, "当前日期预约已满,不能体检!");
        }
        //查看是否是会员不是会员添加会员
        Member member = memberDao.findByTelephone(telephone);
        if (member == null) {
            member = new Member();
            member.setName(name);
            member.setSex(sex);
            member.setIdCard(idCard);
            member.setPhoneNumber(telephone);
            member.setRegTime(new Date());

            memberDao.addMember(member);
        } else {
            //否则是会员查看是否已经预约
            Order order = new Order();
            order.setMemberId(member.getId());
            order.setOrderDate(orderDate);
            order.setSetmealId(setmealId);
            Long count = orderDao.findCountByOrder(order);
            //如果没有预约信息就新建一个预约信息
            if (count > 0) {
                return new Result(false, "您已预约当前套餐,不可重复");
            }
        }

        Order order = new Order();
        order.setMemberId(member.getId());
        order.setOrderDate(orderDate);
        order.setOrderType(Order.ORDERTYPE_WEIXIN);
        order.setOrderStatus(Order.ORDERSTATUS_NO);
        order.setSetmealId(setmealId);
        orderDao.addOrder(order);

        //orderDao.addOrder(order);
        //修改预约设置
        orderSettingDao.editOrderSettingByReservations(orderDate, orderSetting.getReservations() + 1);

        return new Result(true, MessageConstant.ORDER_SUCCESS, order.getId());
    }

    @Override
    public Map findById(Integer id) {
        Map<String,Object> map = orderDao.findByOrderId(id);

        String orderDate = DateUtils.parseDate2String((Date) map.get("orderDate"));
        map.put("orderDate",orderDate);
        return map;
    }
}
