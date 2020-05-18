package com.qingchen.study.mydatasource.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @ClassName DataSourceConfig
 * @description:
 * @author: WangChen
 * @create: 2020-05-04 15:12
 **/
//@Configuration
//@MapperScan(basePackages = "com.qingchen.demo.*.mapper.master", sqlSessionTemplateRef = "masterSqlSessionTemplate")
public class DataSourceMaterConfig {

    @Primary
    //@Bean(name = "dataSourceMaster")
    //@ConfigurationProperties(prefix="spring.datasource.master")
    public DataSource dataSourceMaster(){
        return DruidDataSourceBuilder.create().build();
    }

    //@Bean
    @Primary
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("dataSourceMaster") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setTypeAliasesPackage("com/qingchen/demo/*/model");
        //bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:com/example/datasources/mapper/db1/*.xml"));
        Properties properties = new Properties();
        properties.setProperty("map-underscore-to-camel-case", "true");
        bean.setConfigurationProperties(properties);
        return bean.getObject();
    }

    //@Bean
    @Primary
    public DataSourceTransactionManager masterTransactionManager(@Qualifier("dataSourceMaster") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    //@Bean
    @Primary
    public SqlSessionTemplate masterSqlSessionTemplate(@Qualifier("masterSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }




}
