package com.qingchen.study.ratelimit;

/**
 * @ClassName TimeSlice
 * @description:
 * @author: WangChen
 * @create: 2020-06-19 14:38
 **/
public class TimeSlice {

    private long startTime;
    private long endTime;
    private long passSize;
    private long block;
    private long total;

    public TimeSlice() {
    }

    public TimeSlice(long startTime, long endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public long getStartTime() {
        return startTime;
    }

    public TimeSlice setStartTime(long startTime) {

        this.startTime = startTime;
        return this;
    }

    public long getEndTime() {
        return endTime;
    }

    public TimeSlice setEndTime(long endTime) {
        this.endTime = endTime;
        return this;
    }


    public long getPassSize() {
        return passSize;
    }

    public long getBlock() {
        return block;
    }

    public long getTotal() {
        return total;
    }

    public void blockIncrement(){
        this.block++;
        this.total++;
    }

    public void passIncrement() {
        this.passSize++;
        this.total++;
    }

    @Override
    public String toString() {
        return "TimeSlice{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                ", passSize=" + passSize +
                '}';
    }
}
