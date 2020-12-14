package com.qingchen.study.maptoobj;

import org.junit.Test;

/**
 * @author WangChen
 * @since 2020-11-30 10:57
 **/
public class MapToObjTest {


    @Test
    public void test() throws IllegalAccessException {

        TestModel testModel = new TestModel();
        testModel.setName("wangchen");
        testModel.setAge(24L);

        System.out.println(testModel.toMap());

    }
}
