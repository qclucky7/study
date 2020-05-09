package com.qingchen.study.mydatasource.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import jdk.nashorn.internal.objects.annotations.Property;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @ClassName DataSourceSlaveConfig
 * @description:
 * @author: WangChen
 * @create: 2020-05-04 20:11
 **/
@Configuration
@MapperScan(basePackages = "com.qingchen.demo.*.mapper.slave", sqlSessionTemplateRef = "slaveSqlSessionTemplate")
public class DataSourceSlaveConfig {

    @Bean(name = "dataSourceSlave")
    @ConfigurationProperties(prefix="spring.datasource.slave")
    public DataSource dataSourceSlave(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    public SqlSessionFactory slaveSqlSessionFactory(@Qualifier("dataSourceSlave") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setTypeAliasesPackage("com/qingchen/demo/*/model");
        Properties properties = new Properties();
        properties.setProperty("map-underscore-to-camel-case", "true");
        bean.setConfigurationProperties(properties);
        //bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:com/example/datasources/mapper/db1/*.xml"));
        return bean.getObject();
    }

    @Bean
    public DataSourceTransactionManager slaveTransactionManager(@Qualifier("dataSourceSlave") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public SqlSessionTemplate slaveSqlSessionTemplate(@Qualifier("slaveSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
