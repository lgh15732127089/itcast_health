package com.itheima.service;

import com.itheima.entity.Result;

import java.util.Map;

/**
 * @author : 光辉的mac
 * @ClassName OrderService
 * @date : 2020/9/5 15:56
 */
public interface OrderService {

    Result addOrderByCondition(Map<String, String> map);

    Map findById(Integer id);
}
