package com.qingchen.study.jvm;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executors;

/**
 * @ClassName MyTest
 * @description:
 * @author: WangChen
 * @create: 2020-04-13 16:03
 **/
public class MyTest {

    static class Result{
        private Integer ave;
        private Integer total;

        public Result(Integer ave, Integer total) {
            this.ave = ave;
            this.total = total;
        }

        public Integer getAve() {
            return ave;
        }

        public void setAve(Integer ave) {
            this.ave = ave;
        }

        public Integer getTotal() {
            return total;
        }

        public void setTotal(Integer total) {
            this.total = total;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "ave=" + ave +
                    ", total=" + total +
                    '}';
        }

    }

    @Test
    public void test(){
        List<Result> students = Arrays.asList(
                new Result(90, 360),
                new Result(91, 364),
                new Result(89, 356),
                new Result(92, 368),
                new Result(100, 400)
        );
        students.sort(Comparator.comparing(Result::getTotal).reversed());
    }
}
