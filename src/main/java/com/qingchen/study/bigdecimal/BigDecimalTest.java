package com.qingchen.study.bigdecimal;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * @ClassName BigDecimalTest
 * @description:
 * @author: WangChen
 * @create: 2020-04-25 12:23
 **/
public class BigDecimalTest {


    @Test
    public void myTest(){

        //不用string构造会有精度异常！
        BigDecimal bigDecimal = new BigDecimal(10.511);
        BigDecimal bigDecimal1 = new BigDecimal("10.512");
        BigDecimal bigDecimal2 = BigDecimal.valueOf(10.511);

        System.out.println(bigDecimal);
        System.out.println(bigDecimal1);
        System.out.println(bigDecimal2);

        //ROUND_CEILING      //向正无穷方向舍入
        //ROUND_DOWN         //向零方向舍入
        //ROUND_FLOOR        //向负无穷方向舍入
        //ROUND_HALF_DOWN    //向（距离）最近的一边舍入，除非两边（的距离）是相等,如果是这样，向下舍入,例如1.55 保留一位小数结果为1.5
        //ROUND_HALF_EVEN    //向（距离）最近的一边舍入，除非两边（的距离）是相等,如果是这样，如果保留位数是奇数，使用ROUND_HALF_UP，如果是偶数，使用ROUND_HALF_DOWN
        //ROUND_HALF_UP      //向（距离）最近的一边舍入，除非两边（的距离）是相等,如果是这样，向上舍入, 1.55保留一位小数结果为1.6
        //ROUND_UNNECESSARY  //计算结果是精确的，不需要舍入模式
        //ROUND_UP           //向远离0的方向舍入


        System.out.println("add = " + bigDecimal1.add(bigDecimal2).setScale(1, BigDecimal.ROUND_HALF_UP));
        System.out.println("subtract = " + bigDecimal1.subtract(bigDecimal2.setScale(1, BigDecimal.ROUND_HALF_UP)));
        System.out.println("multiply = " + bigDecimal1.multiply(bigDecimal2).setScale(1, BigDecimal.ROUND_HALF_UP));
        System.out.println("divide = " + bigDecimal1.divide(bigDecimal2, 1, BigDecimal.ROUND_HALF_UP));


    }
}
