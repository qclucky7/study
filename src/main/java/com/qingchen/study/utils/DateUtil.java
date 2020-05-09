package com.qingchen.study.utils;

import org.springframework.util.Assert;

import java.util.Calendar;
import java.util.Date;

/**
 * @Auther: ywx
 * @Date: 18-10-10 15:48
 * @Description: 日期工具
 */
public class DateUtil {
    public static Calendar getCalendar(Date date) {
        Assert.notNull(date, "[DateUtil] [getCalendar] [date null!!!]");
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c;
    }
}
