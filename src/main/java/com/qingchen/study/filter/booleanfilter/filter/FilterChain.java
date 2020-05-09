package com.qingchen.study.filter.booleanfilter.filter;

import com.qingchen.study.filter.booleanfilter.annotation.InitializeOrder;
import com.qingchen.study.filter.booleanfilter.loader.CustomLoader;
import com.qingchen.study.spring.beanlife.BeanConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.lang.reflect.ParameterizedType;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName FilterChain
 * @author: WangChen
 * @create: 2020-03-29 14:11
 **/
public abstract class FilterChain{

    private static Map<Class<?>, List<FilterWrapper>> chainCacheMap = new ConcurrentHashMap<>();

    //private static List<FilterWrapper> filterList = new ArrayList<>();
    private static Logger logger = LoggerFactory.getLogger(FilterChain.class);

    private int index = 0;

    static {
        initialize();
    }

    private static void initialize(){
        logger.debug("[FilterChain][initialize][start]");
        ServiceLoader<Filter> filterServiceLoader = ServiceLoader.load(Filter.class);
        for (Filter filter : filterServiceLoader) {
            ParameterizedType parameterizedType = (ParameterizedType)filter.getClass().getGenericInterfaces()[0];
            Class<?> clazz = (Class<?>)parameterizedType.getActualTypeArguments()[0];
            handleChainMap(clazz, filter);
        }
        logger.debug("[FilterChain][chainCacheMap][initialize][{}]", chainCacheMap.toString());
    }

    private static void handleChainMap(final Class<?> clazz,  Filter filter){
        List<FilterWrapper> filterWrappers = chainCacheMap.getOrDefault(clazz, new ArrayList<>());
        InitializeOrder initializeOrder = filter.getClass().getAnnotation(InitializeOrder.class);
        if (initializeOrder != null ){
            if (initializeOrder.effective()){
                handleSortFilter(filterWrappers, filter, initializeOrder);
            }
        } else {
            fail(clazz, "InitializeOrder annotation does not exist");
        }
        chainCacheMap.put(clazz, filterWrappers);
    }

    private static void handleSortFilter(List<FilterWrapper> list, Filter filter, InitializeOrder initializeOrder){
        int order = initializeOrder.value();
        int index = 0;
        for (; index < list.size(); index++){
            if (list.get(index).getOrder() > order){
                break;
            }
        }
        list.add(index, new FilterWrapper(filter, order));
    }

//    private static int handleFilterOrder(Filter filter){
//        return filter.getClass().getAnnotation(InitializeOrder.class).value();
//    }

    public Object filter(Object o){
        Class<?> clazz = o.getClass();
        Set<Class<?>> classes = chainCacheMap.keySet();
        if (!classes.contains(clazz)){
            throw new IllegalArgumentException("FilterChain does not exist this key");
        }
        return doFilter(o);
    }

    @SuppressWarnings("unchecked")
    public Object doFilter(Object o) {

        List<FilterWrapper> filterList = chainCacheMap.getOrDefault(o.getClass(), Collections.emptyList());
        if (index < filterList.size()) {
            try{
                Filter filter = filterList.get(index).getFilter();
                index++;
                return filter.doFilter(o, this);
            } finally {
                index = 0;
            }

        }
        //index = 0;
        return false;
    }

    static class FilterWrapper{
        private Filter filter;
        private int order;

        FilterWrapper(Filter filter, int order) {
            this.filter = filter;
            this.order = order;
        }

        public Filter getFilter() {
            return filter;
        }

        public void setFilter(Filter filter) {
            this.filter = filter;
        }

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        @Override
        public String toString() {
            return "FilterWrapper{" +
                    "filter=" + filter +
                    ", order=" + order +
                    '}';
        }
    }


    private static void fail(Class<?> service, String msg)
            throws ServiceConfigurationError
    {
       fail(service, msg, null);
    }

    private static void fail(Class<?> service, String msg, Throwable cause)
            throws ServiceConfigurationError
    {
        throw new ServiceConfigurationError(service.getName() + ": " + msg,
                cause);
    }

    public abstract boolean filterEntity(Object o);


}
