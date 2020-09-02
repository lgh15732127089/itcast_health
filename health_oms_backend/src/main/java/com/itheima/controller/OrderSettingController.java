package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.common.MessageConstant;
import com.itheima.customExceptions.CustomException;
import com.itheima.entity.Result;
import com.itheima.pojo.OrderSetting;
import com.itheima.service.OrderSettingService;
import com.itheima.utils.POIUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author : 光辉的mac
 * @ClassName OrderSettingController
 * @date : 2020/9/2 16:37
 */
@RestController
@RequestMapping("/ordersetting")
public class OrderSettingController {

    @Reference
    private OrderSettingService orderSettingService;

    @RequestMapping("/upload")
    public Result upload( MultipartFile excelFile)  {
        /*   基本练习
        //获取文件流
        InputStream inputStream = file.getInputStream();
        //获取表对象
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
        //获取工作簿
        HSSFSheet sheet = workbook.getSheetAt(1);
        //获取行对象
        HSSFRow row = sheet.getRow(1);
        //获取单元格对象
        HSSFCell cell = row.getCell(1);*/
        //获取excel中的所有内容
        try {
            List<String[]> strings = POIUtils.readExcel(excelFile);
            //把excel中的内容转换成bean
            List<OrderSetting> orderSettingList = new ArrayList<>();
            for (String[] string : strings) {
                OrderSetting orderSetting = new OrderSetting();
                Date date = new SimpleDateFormat("yyyy/MM/dd").parse(string[0]);
                orderSetting.setOrderDate(date);
                orderSetting.setNumber(Integer.parseInt(string[1]));
                orderSettingList.add(orderSetting);
            }
            orderSettingService.importAdd(orderSettingList);
        } catch (CustomException e) {
            e.printStackTrace();
            throw e;
        }catch (Exception e) {
            e.printStackTrace();
            try {
                throw new Exception("读取excel出错了");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }

        return new Result(true, MessageConstant.IMPORT_ORDERSETTING_SUCCESS);
    }

    @RequestMapping("/findAllOrderSettingByMonth")
    public Result findAllOrderSettingByMonth(String month){
        List<OrderSetting> orderSettingList = orderSettingService.findAllOrderSettingByMonth(month);
        List<Map<String,Object>> mapList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        for (OrderSetting orderSetting : orderSettingList) {
            Map<String,Object> map = new HashMap<>();
            map.put("date",sdf.format(orderSetting.getOrderDate()));
            map.put("number",orderSetting.getNumber());
            map.put("reservations",orderSetting.getReservations());
            mapList.add(map);
        }

        return new Result(true,MessageConstant.QUERY_ORDER_SUCCESS,mapList);
    }

    @RequestMapping("/editOrderSetting")
    public Result editOrderSetting(@RequestBody OrderSetting orderSetting){
        orderSettingService.editOrderSetting(orderSetting);
        return new Result(true,MessageConstant.ORDERSETTING_SUCCESS);
    }
}
