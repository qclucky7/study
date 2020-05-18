package com.qingchen.study.jpa.config;


import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;

/**
 * @ClassName PrimaryConfig
 * @description:
 * @author: WangChen
 * @create: 2020-05-17 16:16
 **/
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactorySlave",
        transactionManagerRef = "transactionManagerSlave",
        basePackages = {"com.qingchen.study.jpa.slave"}) //设置Repository所在位置
public class SlaveConfig {

    @Autowired
    private JpaProperties jpaProperties;

    @Autowired(required = false)
    private HibernateProperties hibernateProperties;


    @Bean(name = "slaveDataSource")
    @Qualifier("slaveDataSource")
    @ConfigurationProperties(prefix="spring.datasource.slave")
    public DataSource primaryDataSource() {
        return DruidDataSourceBuilder.create().build();
    }


    @Bean(name = "entityManagerSlave")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactorySlave(builder).getObject().createEntityManager();
    }


    @Bean(name = "entityManagerFactorySlave")
    public LocalContainerEntityManagerFactoryBean entityManagerFactorySlave(EntityManagerFactoryBuilder builder) {

        Map<String, Object> properties = hibernateProperties.determineHibernateProperties(
                jpaProperties.getProperties(), new HibernateSettings());

        return builder
                .dataSource(primaryDataSource())
                .properties(properties)
                //设置实体类所在位置
                .packages("com/qingchen/study/jpa/slave/entity")
                .persistenceUnit("primaryPersistenceUnit")
                .build();
    }


    @Bean(name = "transactionManagerSlave")
    public PlatformTransactionManager transactionManagerPrimary(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactorySlave(builder).getObject());
    }
}

