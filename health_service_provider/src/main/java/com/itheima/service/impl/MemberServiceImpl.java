package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.dao.MemberDao;
import com.itheima.pojo.Member;
import com.itheima.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.Map;

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
        Calendar calendar = Calendar.getInstance();


        return null;
    }
}
