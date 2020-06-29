package com.qingchen.study.ratelimit;

import java.util.List;

/**
 * @ClassName StatisticsEntity
 * @description:
 * @author: WangChen
 * @create: 2020-06-20 16:12
 **/
public class StatisticsEntity {

     private String mappingUrl;

     private List<TimeSlice> timeSlices;


    public String getMappingUrl() {
        return mappingUrl;
    }

    public void setMappingUrl(String mappingUrl) {
        this.mappingUrl = mappingUrl;
    }

    public List<TimeSlice> getTimeSlices() {
        return timeSlices;
    }

    public void setTimeSlices(List<TimeSlice> timeSlices) {
        this.timeSlices = timeSlices;
    }

    static class TimeSlice{

         private long pass;
         private long block;
         private long total;
         private String beginTime;
         private String endTime;

        public long getPass() {
            return pass;
        }

        public void setPass(long pass) {
            this.pass = pass;
        }

        public long getBlock() {
            return block;
        }

        public void setBlock(long block) {
            this.block = block;
        }

        public long getTotal() {
            return total;
        }

        public void setTotal(long total) {
            this.total = total;
        }

        public String getBeginTime() {
            return beginTime;
        }

        public void setBeginTime(String beginTime) {
            this.beginTime = beginTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        @Override
        public String toString() {
            return "TimeSlice{" +
                    "pass=" + pass +
                    ", beginTime='" + beginTime + '\'' +
                    ", endTime='" + endTime + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "StatisticsEntity{" +
                "mappingUrl='" + mappingUrl + '\'' +
                ", timeSlices=" + timeSlices +
                '}';
    }
}
