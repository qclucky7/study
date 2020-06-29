package com.qingchen.study.ratelimit;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName WindowQpsControl
 * @description:
 * @author: WangChen
 * @create: 2020-06-18 17:59
 **/
public class WindowQpsController {

    /**
     * 接受请求窗口
     */
    private Long[] accessWindow;
    /**
     * 窗口大小
     */
    private int limit;
    /**
     * 指针位置
     */
    private int curPosition;
    /**
     * 时间间隔
     */
    private long period;

    /**
     * 并发统计窗口
     */
    private TimeSlice[] timeSlices = new TimeSlice[10];

    /**
     * 统计指针
     */
    private int timeCurPosition;
    private int timeSlicesConstant = timeSlices.length - 1 ;

    /**
     * 统计指标
     */
    private long statisticalIndicators = TimeUnit.SECONDS.toMillis(3);

    private final Object lock = new Object();

    /**
     * @param limit    限制次数
     * @param period   时间间隔
     * @param timeUnit 间隔类型
     */
    public WindowQpsController(int limit, int period, TimeUnit timeUnit) {
        if (limit < 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + limit);
        }
        curPosition = 0;
        timeCurPosition = 0;
        this.period = timeUnit.toMillis(period);
        this.limit = limit;
        accessWindow = new Long[limit];
        Arrays.fill(accessWindow, 0L);
        //这底层引用同一个对象。。
        //Arrays.fill(timeSlices, new TimeSlice());
        for (int i = 0; i < timeSlices.length; i++) {
            timeSlices[i] = new TimeSlice(System.currentTimeMillis(),
                    System.currentTimeMillis() + statisticalIndicators);
        }
    }

    public boolean isPass() {
        long curTime = System.currentTimeMillis();
        synchronized (lock) {
            if (curTime >= period + accessWindow[curPosition]) {
                accessWindow[curPosition++] = curTime;

                if (timeSlices[timeCurPosition].getStartTime() <= curTime
                        && curTime <= timeSlices[timeCurPosition].getEndTime()) {
                    timeSlices[timeCurPosition].passIncrement();
                } else {
                    timeCurPosition++;
                    if (timeCurPosition == timeSlices.length) {
                        //数组向左滑动
                        for (int i = 0; i < timeSlicesConstant ; i++) {
                            timeSlices[i] = timeSlices[i + 1];
                        }
                        timeSlices[timeSlicesConstant] = new TimeSlice(curTime ,
                                curTime + statisticalIndicators);
                        //达到阈值后固定索引位置
                        timeCurPosition = timeSlicesConstant;
                    } else {
                        //得到上个时间段的区间
                        long endTime = timeSlices[timeCurPosition - 1].getEndTime();
                        timeSlices[timeCurPosition]
                                .setStartTime(endTime)
                                .setEndTime(endTime + statisticalIndicators);
                    }

                }

                curPosition = curPosition % limit;
                return true;
            } else {
                if (timeSlices[timeCurPosition].getStartTime() <= curTime
                        && curTime <= timeSlices[timeCurPosition].getEndTime()) {
                    timeSlices[timeCurPosition].blockIncrement();
                }
                return false;
            }
        }
    }


    public TimeSlice[] getTimeSlices() {
        return this.timeSlices;
    }
}
