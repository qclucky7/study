package com.qingchen.study.ratelimit;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName LimitStatisticsProcessor
 * @description:
 * @author: WangChen
 * @create: 2020-06-20 15:53
 **/
public class LimitStatisticsProcessor extends AbstractRateLimitSupport{

   private static class LimitStatisticsProcessorInstance{
       private static LimitStatisticsProcessor limitStatisticsProcessor= new LimitStatisticsProcessor();
   }

   public static LimitStatisticsProcessor getInstance(){
       return LimitStatisticsProcessorInstance.limitStatisticsProcessor;
   }

   public List<StatisticsEntity> getStatistic(){
       Map<String, WindowQpsController> statistics = this.getStatistics();
       List<StatisticsEntity> statisticsEntities = new ArrayList<>(statistics.size());
       statistics.forEach((url, windowQpsController) -> {
           StatisticsEntity statisticsEntity = new StatisticsEntity();
           statisticsEntity.setMappingUrl(url);
           TimeSlice[] timeSlices = windowQpsController.getTimeSlices();
           List<StatisticsEntity.TimeSlice> timeSliceList = new ArrayList<>(timeSlices.length);
           for (TimeSlice timeSlice : timeSlices) {
               StatisticsEntity.TimeSlice timeSlice1 = new StatisticsEntity.TimeSlice();
               timeSlice1.setPass(timeSlice.getPassSize());
               timeSlice1.setBlock(timeSlice.getBlock());
               timeSlice1.setTotal(timeSlice.getTotal());
               timeSlice1.setBeginTime(LocalDateTime.ofInstant(Instant.ofEpochMilli(timeSlice.getStartTime()), ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
               timeSlice1.setEndTime(LocalDateTime.ofInstant(Instant.ofEpochMilli(timeSlice.getStartTime()), ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
               timeSliceList.add(timeSlice1);
           }
           statisticsEntity.setTimeSlices(timeSliceList);
           statisticsEntities.add(statisticsEntity);
       });
       return statisticsEntities;
   }

    @Override
    public Map<String, WindowQpsController> getStatistics() {
        return super.getStatistics();
    }

    private LimitStatisticsProcessor(){}
}
