package com.qingchen.study.utils.mybatis;

import org.junit.Test;

/**
 * @ClassName AssertTest
 * @description:
 * @author: WangChen
 * @create: 2020-07-02 10:27
 **/
public class AssertTest {

    @Test
    public void myTest(){

        Assert.notNull(null, "对象为空");
    }

}
