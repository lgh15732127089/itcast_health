package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.common.MessageConstant;
import com.itheima.entity.Result;
import com.itheima.pojo.ReportData;
import com.itheima.service.MemberService;
import com.itheima.service.ReportService;
import com.itheima.service.SetmealService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

/**
 * @author : 光辉的mac
 * @ClassName ReportController
 * @date : 2020/9/8 21:14
 */
@RestController
@RequestMapping("/report")
public class ReportController {

    @Reference
    private SetmealService setmealService;

    @Reference
    private MemberService memberService;

    @Reference
    ReportService reportService;

    @RequestMapping("/getMemberReport")
    public Result getMemberReport() {
        Map<String, Object> resultMap = memberService.getMemberReport();
        return new Result(true, MessageConstant.GET_MEMBER_NUMBER_REPORT_SUCCESS, resultMap);
    }

    @RequestMapping("/getSetmealReport")
    public Result getSetmealReport() {
        //返回的是所有的套餐 setmealNames 和所有的套餐名字和他们预约个数 setmealCount
        Map<String, List<Map<String, String>>> resultMap = new HashMap<>();
        List setmealNames = new ArrayList<>();

        List<Map<String, String>> setmealCount = setmealService.findSetmealAndOrder();

        for (Map<String, String> setmealMap : setmealCount) {
            String name = setmealMap.get("name");
            setmealNames.add(name);
        }
        resultMap.put("setmealNames", setmealNames);
        resultMap.put("setmealCount", setmealCount);

        return new Result(true, MessageConstant.GET_SETMEAL_COUNT_REPORT_SUCCESS, resultMap);
    }

    @RequestMapping("/getBusinessReportData")
    public Result getBusinessReportData() {

        ReportData reportData = reportService.getBusinessReportData();

        return new Result(true, MessageConstant.GET_BUSINESS_REPORT_SUCCESS, reportData);
    }

    @RequestMapping("/exportBusinessReport")
    public void exportBusinessReport(HttpServletResponse response) {
        try {
            ReportData reportData = reportService.getBusinessReportData();
            //相对路径,从服务器tomcat读取资源文件
            InputStream inputStream = this.getClass().getResourceAsStream("/report_template.xlsx");
            //获取excel对象
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            //获取sheet对象
            XSSFSheet sheet = workbook.getSheetAt(0);
            //获取行对象
            Row row = sheet.getRow(2);
            //获取单元格对象
            Cell cell = row.getCell(5);
            cell.setCellValue(reportData.getReportDate());
            //设置今日新增会员数
            //获取行对象
            row = sheet.getRow(4);
            //获取单元格
            cell = row.getCell(5);
            //设置单元格的数据
            cell.setCellValue(String.valueOf(reportData.getTodayNewMember()));


            //设置总会员数
            //获取行对象
            row = sheet.getRow(4);
            //获取单元格
            cell = row.getCell(7);
            //设置单元格的数据
            cell.setCellValue(String.valueOf(reportData.getTotalMember()));


            //设置本周新增会员数
            //获取行对象
            row = sheet.getRow(5);
            //获取单元格
            cell = row.getCell(5);
            //设置单元格的数据
            cell.setCellValue(String.valueOf(reportData.getThisWeekNewMember()));

            //设置本月新增会员数
            //获取行对象
            row = sheet.getRow(5);
            //获取单元格
            cell = row.getCell(7);
            //设置单元格的数据
            cell.setCellValue(String.valueOf(reportData.getThisMonthNewMember()));


            //设置今日预约数
            //获取行对象
            row = sheet.getRow(7);
            //获取单元格
            cell = row.getCell(5);
            //设置单元格的数据
            cell.setCellValue(String.valueOf(reportData.getTodayOrderNumber()));

            //设置今日到诊数
            //获取行对象
            row = sheet.getRow(7);
            //获取单元格
            cell = row.getCell(7);
            //设置单元格的数据
            cell.setCellValue(String.valueOf(reportData.getTodayVisitsNumber()));


            //设置本周预约数
            //获取行对象
            row = sheet.getRow(8);
            //获取单元格
            cell = row.getCell(5);
            //设置单元格的数据
            cell.setCellValue(String.valueOf(reportData.getThisWeekOrderNumber()));

            //设置本周到诊数
            //获取行对象
            row = sheet.getRow(8);
            //获取单元格
            cell = row.getCell(7);
            //设置单元格的数据
            cell.setCellValue(String.valueOf(reportData.getThisWeekVisitsNumber()));


            //设置本月预约数
            //获取行对象
            row = sheet.getRow(9);
            //获取单元格
            cell = row.getCell(5);
            //设置单元格的数据
            cell.setCellValue(String.valueOf(reportData.getThisMonthOrderNumber()));

            //设置本月到诊数
            //获取行对象
            row = sheet.getRow(9);
            //获取单元格
            cell = row.getCell(7);
            //设置单元格的数据
            cell.setCellValue(String.valueOf(reportData.getThisMonthVisitsNumber()));

            //声明行号
            int rownum = 12;
            for (Map<String, Object> map : reportData.getHotSetmeal()) {
                //获取行对象
                row = sheet.getRow(rownum);
                //
                cell = row.getCell(4);
                cell.setCellValue(String.valueOf(map.get("name")));
                //获取行，给行赋值
                cell = row.getCell(5);
                cell.setCellValue(String.valueOf(map.get("setmeal_count")));
                //获取行，给行赋值
                cell = row.getCell(6);
                cell.setCellValue(String.valueOf(map.get("proportion")));

                rownum++;
            }

            //4. 下载excel到本地
            // 通过输出流进行文件下载
            ServletOutputStream out = response.getOutputStream();
            //指定附件的格式为excel
            response.setContentType("application/vnd.ms-excel");
            //attachment 以附件下载
            //下载弹出框架中的默认的文件名
            response.setHeader("content-Disposition", "attachment;filename=" + reportData.getReportDate() + "_report.xlsx");
            //输出流下载
            workbook.write(out);
            out.flush();
            out.close();
            workbook.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
