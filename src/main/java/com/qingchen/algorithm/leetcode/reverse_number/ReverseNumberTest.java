package com.qingchen.algorithm.leetcode.reverse_number;

import org.junit.Test;

/**
 * @ClassName ReverseNumberTest
 * @description:
 * @author: WangChen
 * @create: 2020-10-12 15:58
 **/
public class ReverseNumberTest {


    @Test
    public void startTest(){
        System.out.println(reverse(-521));
    }

    public int reverse(int x) {
        long n = 0;
        while(x != 0) {
            n = n*10 + x%10;
            x = x/10;
        }
        return (int)n==n? (int)n:0;
    }


}
