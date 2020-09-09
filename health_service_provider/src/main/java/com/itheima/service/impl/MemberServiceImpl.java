package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.dao.MemberDao;
import com.itheima.pojo.Member;
import com.itheima.service.MemberService;
import com.itheima.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author : 光辉的mac
 * @ClassName MemberServiceImpl
 * @date : 2020/9/5 19:51
 */
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao memberDao;

    @Override
    public Member findByTelephone(String telephone) {
        return memberDao.findByTelephone(telephone);
    }

    @Override
    public void addMember(Member member) {
        memberDao.addMember(member);
    }

    @Override
    public Map<String, Object> getMemberReport() {
        //[months:{1,2,3}
        //memberCount:{20,30,40}
        // ]
        Calendar calendar = Calendar.getInstance();
        //先获取一年前的日期
        calendar.add(Calendar.MONTH,-12);
        List months =  new ArrayList<>();
        List memberCount =  new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        //获取每个月
        for (int i = 0; i < 12; i++) {
            calendar.add(Calendar.MONTH,1);
            Date date = calendar.getTime();
            //把每个月的日期添加到集合中
            String strDate = simpleDateFormat.format(date);
            months.add(strDate);
            //获取每个月的最后一天
            Date lastDay4Month = DateUtils.getLastDay4Month(strDate);
            //根据最后一天获取会员数
            Long count = memberDao.findCountByLastDay(lastDay4Month);
            memberCount.add(count);
        }
        Map<String, Object> map = new HashMap<>();

        map.put("months",months);
        map.put("memberCount",memberCount);
        return map;
    }
}
