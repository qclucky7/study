package com.qingchen.study.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.alibaba.excel.converters.string.StringImageConverter;

import java.util.Date;

/**
 * @ClassName DemoDate
 * @description:
 * @author: WangChen
 * @create: 2020-03-14 13:42
 **/

@ContentRowHeight(value = 30)
@HeadRowHeight(value = 20)
@ColumnWidth(value = 20)
public class DemoDate1 extends AbstractExcel{

    @ExcelProperty(value = {"统计","姓名"})
    private String name;

    @ColumnWidth(value = 20)
    @ExcelProperty(value = {"统计","电话"})
    private long phone;

    @ExcelProperty(value = {"统计","地址"})
    private String address;

    @ColumnWidth(35)
    @DateTimeFormat("yyyy年MM月dd日HH时mm分ss秒")
    @ExcelProperty(value = {"统计","时间"})
    private Date date;

    /**
     * 我想写到excel 用百分比表示
     */
    @NumberFormat("#.##%")
    @ExcelProperty(value = {"统计","数字标题"})
    private Double doubleData;

    @ColumnWidth(value = 30)
    @ExcelProperty(value = {"统计", "图片"}, converter = StringImageConverter.class)
    private String filePath;


    public Double getDoubleData() {
        return doubleData;
    }

    public void setDoubleData(Double doubleData) {
        this.doubleData = doubleData;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
