package com.qingchen.study.time;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.*;

import static com.qingchen.study.time.TimeUtils.YEAR_MONTH_DAY_HOURS_MIN_SEN;

/**
 * @ClassName Java8Time
 * @description:
 * @author: WangChen
 * @create: 2020-04-22 13:05
 **/
public class Java8Time {

    public static void main(String[] args) {


        LocalDate localDate = LocalDate.now();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String format = dateTimeFormatter.format(localDate);
        System.out.println(format);

        LocalDate parse = LocalDate.parse("20200516", dateTimeFormatter);
        System.out.println(parse);

        String format1 = localDate.format(dateTimeFormatter);
        System.out.println(format1);
        LocalDate localDate1 = localDate.withDayOfMonth(1);
        int dayOfMonth = localDate.getDayOfMonth();
        int i = localDate.lengthOfMonth();
        System.out.println(i);
        System.out.println(dayOfMonth);
        System.out.println(localDate1);


        LocalDateTime localDateTime = localDate.atStartOfDay();

        System.out.println("localDateTime = " + localDateTime);


        long l = Instant.now().toEpochMilli();
        String format2 = LocalDateTime.ofInstant(Instant.now(Clock.systemDefaultZone()), ZoneId.systemDefault())
                .format(YEAR_MONTH_DAY_HOURS_MIN_SEN);

        LocalDate localDate2 = LocalDateTime.ofInstant(Instant.now(Clock.systemDefaultZone()), ZoneId.systemDefault())
                .toLocalDate();

        LocalTime localTime = LocalDateTime.ofInstant(Instant.now(Clock.systemDefaultZone()), ZoneId.systemDefault())
                .toLocalTime();


        LocalDate with = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());

        String format3 = LocalDateTime.of(with, LocalTime.MIDNIGHT).format(YEAR_MONTH_DAY_HOURS_MIN_SEN);

        LocalDateTime of = LocalDateTime.of(with, LocalTime.MAX);

        LocalDateTime of2 = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        LocalDateTime of3 = LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT);
        LocalDateTime of1 = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);

        System.out.println(of1);
        System.out.println(of2);
        System.out.println(of3);

        long l1 = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        System.out.println(l);
        System.out.println(l1);
        System.out.println(System.currentTimeMillis());


        System.out.println(format3);
        System.out.println(with);

        System.out.println(localDate2);
        System.out.println(localTime);

        System.out.println("----------------");
        System.out.println(format2);
        long epochSecond = Instant.now().getEpochSecond();
        System.out.println(epochSecond);
        System.out.println(l);


        System.out.println("---------------");


        LocalDate of4 = LocalDate.of(2019, 4, 16);
        LocalDate of10 = LocalDate.of(2020, 5, 20);
        int value = of4.getDayOfWeek().getValue();

        LocalDate localDate3 = of4.minusDays(value - 1);
        LocalDate localDate4 = of4.plusDays(7 - value);

        LocalDateTime of5 = LocalDateTime.of(localDate3, LocalTime.MIN);
        LocalDateTime of6 = LocalDateTime.of(localDate3, LocalTime.MAX);

        System.out.println(of4);
        System.out.println(localDate3);
        System.out.println(localDate4);



        System.out.println(of5);
        System.out.println(of6);

        System.out.println("111111111111111111111111111111111111111");
        System.out.println(of4.with(TemporalAdjusters.firstDayOfMonth()));
        System.out.println(of4.with(TemporalAdjusters.firstDayOfYear()));
        System.out.println(of4.with(TemporalAdjusters.firstDayOfNextMonth()));
        System.out.println(of4.with(TemporalAdjusters.firstDayOfNextYear()));
        System.out.println(of4.with(TemporalAdjusters.lastDayOfMonth()));
        System.out.println(of4.with(TemporalAdjusters.lastDayOfYear()));
        System.out.println("22222222222222222222222222222222");
        System.out.println(of4.with(TemporalAdjusters.next(of4.getDayOfWeek())));
        System.out.println(of4.with(TemporalAdjusters.firstInMonth(of4.getDayOfWeek())));
        System.out.println(of4.with(TemporalAdjusters.lastInMonth(of4.getDayOfWeek())));
        System.out.println(of4.with(TemporalAdjusters.nextOrSame(of4.getDayOfWeek())));
        System.out.println(of4.with(TemporalAdjusters.previous(of4.getDayOfWeek())));
        System.out.println(of4.with(TemporalAdjusters.previousOrSame(of4.getDayOfWeek())));

        LocalDateTime now = LocalDateTime.of(2021, 4, 22, 9, 50,30);
        LocalDateTime of7 = LocalDateTime.of(2020, 4, 22, 10, 20, 20);

        long between = ChronoUnit.WEEKS.between(now, of7);

        System.out.println(between);

        LocalDate now1 = LocalDate.of(2020,4,26);
        LocalDate localDate5 = now1.plusDays(7 - now1.getDayOfWeek().getValue());

        System.out.println(now1.isEqual(localDate5));

        System.out.println(TimeUtils.isLastDayOfWeek(now1));

        System.out.println(TimeUtils.betweenTimeOfMonths(LocalDateTime.of(2020,7,18,10,20,11),
                LocalDateTime.of(2020,12,18,10,20,11)));


    }
}
