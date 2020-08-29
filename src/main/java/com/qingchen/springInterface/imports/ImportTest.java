package com.qingchen.springInterface.imports;

import org.springframework.beans.factory.InitializingBean;

/**
 * @ClassName ImportTest
 * @description:
 * @author: WangChen
 * @create: 2020-08-29 13:56
 **/
public class ImportTest implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("--------------------> import注解导入ioc!");
    }
}
