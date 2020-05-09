package com.qingchen.study.mydatasource;

import com.qingchen.demo.mydemo.mapper.master.TestMapper;
import com.qingchen.demo.mydemo.mapper.slave.SlaveTestMapper;
import com.qingchen.demo.mydemo.model.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName DataSourceTest
 * @description:
 * @author: WangChen
 * @create: 2020-05-04 16:53
 **/
@RestController
//@DataSourceSelector(dataSource = DynamicDataSource.DatabaseType.dataSourceSlave)
public class DataSourceTest {

    @Resource
    private TestMapper mapper;

    @Resource
    private SlaveTestMapper mapperSlave;

    @GetMapping("/query/{id}")
    @DataSourceSelector(dataSource = DynamicDataSource.DatabaseType.dataSourceSlave)
    public void myTest(@PathVariable("id") long id){
        Message query = mapper.query(id);
        Message query1 = mapperSlave.query(id);

        System.out.println(query);
        System.out.println(query1);
    }

    @GetMapping("/query_slave/{id}")
    @DataSourceSelector(dataSource = DynamicDataSource.DatabaseType.dataSourceSlave)
    public void myTestSlave(@PathVariable("id") long id){
        Message query = mapperSlave.query(id);
        System.out.println(query);
    }
}
