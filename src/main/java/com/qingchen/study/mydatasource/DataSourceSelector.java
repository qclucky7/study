package com.qingchen.study.mydatasource;

import java.lang.annotation.*;

/**
 * @InterfaceName DataSourceSelector
 * @description:
 * @author: WangChen
 * @create: 2020-05-04 16:33
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface DataSourceSelector {

    DynamicDataSource.DatabaseType dataSource() default DynamicDataSource.DatabaseType.dataSourceMaster;
}
