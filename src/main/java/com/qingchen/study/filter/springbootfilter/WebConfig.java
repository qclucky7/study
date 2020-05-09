package com.qingchen.study.filter.springbootfilter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.servlet.DispatcherType;

/**
 * @ClassName WebConfig
 * @description:
 * @author: WangChen
 * @create: 2020-03-31 20:53
 **/
@Configuration
//@ConditionalOnProperty(prefix = "webFilter", value = "enable", havingValue = "true")
//@PropertySource("classpath:application.yaml")
//@Conditional(MyCondition.class)
@ConditionalOnExpression("${webFilter.enable}")
public class WebConfig {

    @Bean
    public FilterRegistrationBean<MyFilter> myFilter() {
        FilterRegistrationBean<MyFilter> filterReg = new FilterRegistrationBean<>();
        filterReg.setFilter(new MyFilter());
        //优先级
        filterReg.setOrder(1);
        filterReg.setDispatcherTypes(DispatcherType.REQUEST);
        //匹配路径
        filterReg.addUrlPatterns("/*");
        //filterReg.addInitParameter("exclusions","/test");
        return filterReg;
    }

    @Bean
    public FilterRegistrationBean<MyFilter2> myFilter2(){
        FilterRegistrationBean<MyFilter2> filterReg = new FilterRegistrationBean<>();
        filterReg.setFilter(new MyFilter2());
        //优先级
        filterReg.setOrder(2);
        filterReg.setDispatcherTypes(DispatcherType.REQUEST);
        //匹配路径
        filterReg.addUrlPatterns("/*");
        //filterReg.addInitParameter("exclusions","/test");
        return filterReg;
    }
}
