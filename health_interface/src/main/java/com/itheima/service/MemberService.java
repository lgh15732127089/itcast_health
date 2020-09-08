package com.itheima.service;

import com.itheima.pojo.Member;

import java.util.Map;

/**
 * @author : 光辉的mac
 * @ClassName MemberService
 * @date : 2020/9/5 19:51
 */
public interface MemberService {

    Member findByTelephone(String telephone);

    void addMember(Member member);

    Map<String, Object> getMemberReport();

}
