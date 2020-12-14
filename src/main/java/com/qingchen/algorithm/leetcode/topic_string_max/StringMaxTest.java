package com.qingchen.algorithm.leetcode.topic_string_max;

import org.junit.Test;

/**
 * @ClassName StringMaxTest
 * @description:
 * @author: WangChen
 * @create: 2020-10-12 14:41
 **/
public class StringMaxTest {


    @Test
    public void calculate(){
        String str = "aaaabcdefffffghjitkm";
        System.out.println(this.strMax(str));
    }


    private int strMax(String str) {
        // 记录字符上一次出现的位置
        int[] last = new int[128];
        for (int i = 0; i < 128; i++) {
            last[i] = -1;
        }

        int n = str.length();

        int res = 0;
        // 窗口开始位置
        int start = 0;
        for (int i = 0; i < n; i++) {
            int index = str.charAt(i);
            start = Math.max(start, last[index] + 1);
            res = Math.max(res, i - start + 1);
            last[index] = i;
        }

        return res;

    }
}
