package com.qingchen.study.word;

import org.junit.Test;

/**
 * @ClassName MyWordStudy
 * @description:
 * @author: WangChen
 * @create: 2020-05-13 16:57
 **/
public class MyWordStudy {

    /**
     *
     * @encapsulates封装
     * @attributes属性
     * @achieve实现
     * @section部分
     * @prepares准备
     */

    @Test
    public void test9(){

        int modifiers = MyWordStudy.class.getModifiers();

        System.out.println(modifiers);
    }
}
