package com.qingchen.study.time;

import com.qingchen.study.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

/**
 * @ClassName TimeUtils
 * @description: 日期工具类
 * @author: WangChen
 * @create: 2020-04-22 13:58
 **/
public class TimeUtils {

    private static final Logger log = LoggerFactory.getLogger(TimeUtils.class);

    /**
     * 格式化日期 yyyy-MM-dd
     */
    public static final DateTimeFormatter YEAR_MONTH_DAY0 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    /**
     * 格式化日期 yyyyMMdd
     */
    public static final DateTimeFormatter YEAR_MONTH_DAY1 = DateTimeFormatter.ofPattern("yyyyMMdd");
    /**
     * 格式化日期 yyyy-MM-dd HH:mm:ss
     */
    public static final DateTimeFormatter YEAR_MONTH_DAY_HOURS_MIN_SEN = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    /**
     * 格式化日期 yyyy年MM月dd日 HH时:mm分:ss秒
     */
    public static final DateTimeFormatter YEAR_MONTH_DAY_HOURS_MIN_SEN_CN = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH时:mm分:ss秒");
    /**
     * 格式化日期 yyyy年MM月dd日
     */
    public static final DateTimeFormatter YEAR_MONTH_DAY_CN = DateTimeFormatter.ofPattern("yyyy年MM月dd日");


    //******************************************日期转换******************************************/

    /**
     * {@link #YEAR_MONTH_DAY0} 得到yyyy-MM-dd日期
     *
     * @return String
     */
    public static String getLocalDate0(LocalDate localDate) {
        if (localDate == null) {
            return null;
        }
        return localDate.format(YEAR_MONTH_DAY0);
    }

    /**
     * {@link #YEAR_MONTH_DAY0} 得到yyyy-MM-dd日期
     *
     * @return String
     */
    public static String getLocalDate0(LocalDate localDate, DateTimeFormatter dateTimeFormatter) {
        if (dateTimeFormatter == null || localDate == null) {
            return null;
        }
        return localDate.format(dateTimeFormatter);
    }

    /**
     * {@link #YEAR_MONTH_DAY1} 得到yyyyMMdd日期
     *
     * @return String
     */
    public static String getLocalDate1(LocalDate localDate) {
        if (localDate == null) {
            return null;
        }
        return localDate.format(YEAR_MONTH_DAY1);
    }

    /**
     * {@link #YEAR_MONTH_DAY_HOURS_MIN_SEN} 得到yyyy-MM-dd HH:mm:ss日期
     *
     * @return String
     */
    public static String getLocalDate2(LocalDate localDate) {
        if (localDate == null) {
            return null;
        }
        return localDate.format(YEAR_MONTH_DAY_HOURS_MIN_SEN);
    }

    /**
     * {@link #YEAR_MONTH_DAY_CN} 得到 yyyy年MM月dd日 日期
     *
     * @return String
     */
    public static String getLocalDateCn(LocalDate localDate) {
        if (localDate == null) {
            return null;
        }
        return localDate.format(YEAR_MONTH_DAY_CN);
    }

    /**
     * 字符串转成日期
     * {@link #YEAR_MONTH_DAY_HOURS_MIN_SEN#YEAR_MONTH_DAY}
     *
     * @param localDate
     * @param dateTimeFormatter
     * @return LocalDate
     */
    public static LocalDate parseLocalDate(String localDate, DateTimeFormatter dateTimeFormatter) {
        if (StringUtils.isEmpty(localDate) || dateTimeFormatter == null) {
            return null;
        }
        return LocalDate.parse(localDate, dateTimeFormatter);
    }


    /**
     * 得到当前时间戳
     *
     * @return long
     */
    public static long getCurrentTime() {
        return Instant.now().toEpochMilli();
    }

    /**
     * LocalDateTime转成时间戳
     *
     * @param localDateTime
     * @return long
     */
    public static long toCurrentTime(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return 0;
        }
        return localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    /**
     * 时间戳转成LocalDateTime
     *
     * @param timestamp
     * @return LocalDateTime
     */
    public static LocalDateTime timestampToDatetime(long timestamp) {
        if (timestamp < 0) {
            return null;
        }
        Instant instant = Instant.ofEpochMilli(timestamp);
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    /**
     * LocalDateTime转成LocalDate
     *
     * @param localDateTime
     * @return LocalDateTime
     */
    public static LocalDate localDateTimeToLocalDate(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        return localDateTime.toLocalDate();
    }


    /**
     * 时间戳转成LocalDate
     *
     * @param timestamp
     * @return LocalDateTime
     */
    public static LocalDate timestampToLocalDate(long timestamp) {
        return localDateTimeToLocalDate(timestampToDatetime(timestamp));
    }


    //**********************************得到日常日期时间戳*******************************************/

    /**
     * 得到某天开始或结束时间戳, 默认为今天
     *
     * @return long
     */
    public static long getStartTimeOfToday(boolean start) {
        return getStartTimeOfToday(null, start);
    }


    /**
     * 得到某天开始或结束时间戳, 默认为今天
     *
     * @return long
     */
    public static long getStartTimeOfToday(LocalDate localDate, boolean start) {
        if (localDate == null) {
            localDate = LocalDate.now();
        }
        if (start) {
            return toCurrentTime(LocalDateTime.of(localDate, LocalTime.MIN));
        }
        return toCurrentTime(LocalDateTime.of(localDate, LocalTime.MAX));
    }

    /**
     * 得到这个日期周的开始时间戳或结束时间戳 不传默认为这周
     *
     * @param start
     * @return long
     */
    public static long getStartOrEndDayOfWeek(boolean start) {
        return getStartOrEndDayOfWeek(null, start);
    }

    /**
     * 得到这个日期周的开始时间戳或结束时间戳 不传默认为这周
     *
     * @param localDate
     * @param start
     * @return long
     */
    public static long getStartOrEndDayOfWeek(LocalDate localDate, boolean start) {
        if (localDate == null) {
            localDate = LocalDate.now();
        }
        //offset
        int value = localDate.getDayOfWeek().getValue();
        if (start) {
            return toCurrentTime(LocalDateTime.of(localDate.minusDays(value - 1), LocalTime.MIN));
        } else {
            return toCurrentTime(LocalDateTime.of(localDate.minusDays(7 - value), LocalTime.MAX));
        }

    }

    /**
     * 得到这个日期月的开始时间戳或结束时间戳 不传默认为这个月
     *
     * @param start
     * @return long
     */
    public static long getStartOrEndDayOfMonth(boolean start) {
        return getStartOrEndDayOfMonth(null, start);
    }

    /**
     * 得到这个日期月的开始时间戳或结束时间戳 不传默认为这个月
     *
     * @param localDate
     * @param start
     * @return long
     */
    public static long getStartOrEndDayOfMonth(LocalDate localDate, boolean start) {
        if (localDate == null) {
            localDate = LocalDate.now();
        }
        if (start) {
            return toCurrentTime(LocalDateTime.of(localDate.with(TemporalAdjusters.firstDayOfMonth()), LocalTime.MIN));
        } else {
            return toCurrentTime(LocalDateTime.of(localDate.with(TemporalAdjusters.lastDayOfMonth()), LocalTime.MAX));
        }

    }

    /**
     * 得到这个日期年的开始时间戳或结束时间戳 不传默认为今年
     *
     * @param start
     * @return long
     */
    public static long getStartOrEndDayOfYears(boolean start) {
        return getStartOrEndDayOfYears(null, start);
    }

    /**
     * 得到这个日期年的开始时间戳或结束时间戳 不传默认为今年
     *
     * @param localDate
     * @param start
     * @return long
     */
    public static long getStartOrEndDayOfYears(LocalDate localDate, boolean start) {
        if (localDate == null) {
            localDate = LocalDate.now();
        }
        if (start) {
            return toCurrentTime(LocalDateTime.of(localDate.with(TemporalAdjusters.firstDayOfYear()), LocalTime.MIN));
        } else {
            return toCurrentTime(LocalDateTime.of(localDate.with(TemporalAdjusters.lastDayOfYear()), LocalTime.MAX));
        }

    }

    //*************************************判断日期*****************************************************/

    /**
     * 判断今天是否是周末
     *
     * @param localDate
     * @return
     */
    public static boolean isLastDayOfWeek(LocalDate localDate) {
        if (localDate == null) {
            return false;
        }
        return localDate.isEqual(localDate.plusDays(7 - localDate.getDayOfWeek().getValue()));
    }

    /**
     * 判断今天是否是周一
     *
     * @param localDate
     * @return
     */
    public static boolean isFirstDayOfWeek(LocalDate localDate) {
        if (localDate == null) {
            return false;
        }
        return localDate.isEqual(localDate.minusDays(localDate.getDayOfWeek().getValue() - 1));
    }

    /**
     * 判断今天是否是这个月第一天
     * @param localDate
     * @return
     */
    public static boolean isFirstDayOfMonth(LocalDate localDate){
        if (localDate == null) {
            return false;
        }
        return localDate.isEqual(localDate.with(TemporalAdjusters.firstDayOfMonth()));
    }

    /**
     * 判断今天是否是这个月末
     * @param localDate
     * @return
     */
    public static boolean isLastDayOfMonth(LocalDate localDate){
        if (localDate == null) {
            return false;
        }
        return localDate.isEqual(localDate.with(TemporalAdjusters.lastDayOfMonth()));
    }

    /**
     * 判断今天是否是这个今年第一天
     * @param localDate
     * @return
     */
    public static boolean isFirstDayOfYears(LocalDate localDate){
        if (localDate == null) {
            return false;
        }
        return localDate.isEqual(localDate.with(TemporalAdjusters.firstDayOfYear()));
    }

    /**
     * 判断今天是否是这个今天最后一天
     * @param localDate
     * @return
     */
    public static boolean isLastDayOfYears(LocalDate localDate){
        if (localDate == null) {
            return false;
        }
        return localDate.isEqual(localDate.with(TemporalAdjusters.lastDayOfYear()));
    }


    //**************************************计算时间区间*************************************************/

    /**
     * 计算两个时间相差区间。
     *
     * @param localDateTime0
     * @param localDateTime1
     * @return
     */
    public static long betweenTime(LocalDateTime localDateTime0, LocalDateTime localDateTime1, ChronoUnit chronoUnit) {
        if (localDateTime0.isAfter(localDateTime1)) {
            return chronoUnit.between(localDateTime1, localDateTime0);
        }
        return chronoUnit.between(localDateTime0, localDateTime1);
    }

    /**
     * 计算两个时间相差多少小时 只返回整数！
     *
     * @param localDateTime0
     * @param localDateTime1
     * @return
     */
    public static long betweenTimeOfSeconds(LocalDateTime localDateTime0, LocalDateTime localDateTime1) {
        if (localDateTime0.isAfter(localDateTime1)) {
            return ChronoUnit.SECONDS.between(localDateTime1, localDateTime0);
        }
        return ChronoUnit.SECONDS.between(localDateTime0, localDateTime1);
    }

    /**
     * 计算两个时间相差多少小时 只返回整数！
     *
     * @param localDateTime0
     * @param localDateTime1
     * @return
     */
    public static long betweenTimeOfHours(LocalDateTime localDateTime0, LocalDateTime localDateTime1) {
        if (localDateTime0.isAfter(localDateTime1)) {
            return ChronoUnit.HOURS.between(localDateTime1, localDateTime0);
        }
        return ChronoUnit.HOURS.between(localDateTime0, localDateTime1);
    }

    /**
     * 计算两个时间相差多少天 只返回整数！
     *
     * @param localDateTime0
     * @param localDateTime1
     * @return
     */
    public static long betweenTimeOfDay(LocalDateTime localDateTime0, LocalDateTime localDateTime1) {
        if (localDateTime0.isAfter(localDateTime1)) {
            return ChronoUnit.DAYS.between(localDateTime1, localDateTime0);
        }
        return ChronoUnit.DAYS.between(localDateTime0, localDateTime1);
    }


    /**
     * 计算两个时间相差多少周 只返回整数！
     *
     * @param localDateTime0
     * @param localDateTime1
     * @return
     */
    public static long betweenTimeOfWeek(LocalDateTime localDateTime0, LocalDateTime localDateTime1) {
        if (localDateTime0.isAfter(localDateTime1)) {
            return ChronoUnit.WEEKS.between(localDateTime1, localDateTime0);
        }
        return ChronoUnit.WEEKS.between(localDateTime0, localDateTime1);
    }


    /**
     * 计算两个时间相差多少月 只返回整数！
     *
     * @param localDateTime0
     * @param localDateTime1
     * @return
     */
    public static long betweenTimeOfMonths(LocalDateTime localDateTime0, LocalDateTime localDateTime1) {
        if (localDateTime0.isAfter(localDateTime1)) {
            return ChronoUnit.MONTHS.between(localDateTime1, localDateTime0);
        }
        return ChronoUnit.MONTHS.between(localDateTime0, localDateTime1);
    }


    /**
     * 计算两个时间相差多少年 只返回整数！
     *
     * @param localDateTime0
     * @param localDateTime1
     * @return
     */
    public static long betweenTimeOfYears(LocalDateTime localDateTime0, LocalDateTime localDateTime1) {
        if (localDateTime0.isAfter(localDateTime1)) {
            return ChronoUnit.YEARS.between(localDateTime1, localDateTime0);
        }
        return ChronoUnit.YEARS.between(localDateTime0, localDateTime1);
    }


    private TimeUtils() {
    }

}
