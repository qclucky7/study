package com.qingchen.study.ratelimit;

import com.qingchen.study.globalexception.Result;
import com.qingchen.study.time.TimeUtils;
import org.junit.Test;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName RateLimitTest
 * @description:
 * @author: WangChen
 * @create: 2020-06-18 18:00
 **/
@RestController
@RequestMapping("/v1")
public class RateLimitTest {


    @RateLimit(accessible = 2)
    @GetMapping("/limit")
    public Result limit(){

        return Result.ofSuccessMsg("成功返回！！");
    }

    @RateLimit
    @GetMapping("/limit1")
    public Result limit2(){

        return Result.ofSuccessMsg("成功返回！！");
    }

    @GetMapping("/slice")
    public Result<List<StatisticsEntity>> limit1(){

        List<StatisticsEntity> statistic = LimitStatisticsProcessor.getInstance().getStatistic();

        System.out.println(statistic.toString());

        return Result.ofSuccess(statistic);
    }


    @Test
    public void test(){

        LocalDateTime localDateTime = TimeUtils.timestampToDatetime(System.currentTimeMillis());
        System.out.println(localDateTime);

        String format = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        System.out.println(format);
    }

}
