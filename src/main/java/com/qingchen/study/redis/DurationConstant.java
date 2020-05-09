package com.qingchen.study.redis;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * @ClassName DurationConstant
 * @description:
 * @author: WangChen
 * @create: 2020-04-16 18:17
 **/
public class DurationConstant {

    public static final long ONE_HOUR =  ChronoUnit.HOURS.getDuration().getSeconds();

    public static final long TWO_HOUR = Duration.ofHours(2).getSeconds();

    public static final long HALF_DAYS =  ChronoUnit.HALF_DAYS.getDuration().getSeconds();

    public static final long ONE_DAY =  ChronoUnit.DAYS.getDuration().getSeconds();

    public static final long ONE_WEEK =  ChronoUnit.WEEKS.getDuration().getSeconds();


}
