package com.qingchen.study.beancopy;


import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.qingchen.study.utils.mybatis.ClassUtils;
import net.sf.cglib.beans.BeanMap;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @ClassName BeanCopyUtils
 * @description:
 * @author: WangChen
 * @create: 2020-06-29 08:45
 **/
public class BeanCopyUtils extends BeanUtils {


    /**
     * @param sources
     * @param target
     * @return
     */
    public static <S, T> Set<T> copySetProperties(Set<S> sources, Supplier<Set> setType, Supplier<T> target) {
        return copySetProperties(sources, setType, target,null);
    }


    /**
     * @param sources
     * @param target
     * @param callBackFunction
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <S, T> Set<T> copySetProperties(Set<S> sources, Supplier<Set> setType, Supplier<T> target, BeanCopyCallBack<S, T> callBackFunction) {

        Set<T> result = setType.get();
        for (S source : sources) {
            T t = target.get();
            copyProperties(source, t);
            result.add(t);
            if (callBackFunction != null) {
                callBackFunction.execute(source, t);
            }
        }
        return result;
    }

    /**
     * @param sources
     * @param target
     * @return
     */
    public static <S, T> List<T> copyListProperties(List<S> sources, Supplier<T> target) {
        return copyListProperties(sources, ArrayList::new, target, null);
    }

    /**
     * @param sources
     * @param target
     * @param callBackFunction
     * @return
     */
    public static <S, T> List<T> copyListProperties(List<S> sources, Supplier<T> target, BeanCopyCallBack<S, T> callBackFunction) {

        List<T> result = new ArrayList<>(sources.size());
        for (S source : sources) {
            T t = target.get();
            copyProperties(source, t);
            result.add(t);
            if (callBackFunction != null) {
                callBackFunction.execute(source, t);
            }
        }
        return result;
    }

    /**
     * @param sources
     * @param target
     * @param callBackFunction
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <S, T> List<T> copyListProperties(List<S> sources, Supplier<List> listType, Supplier<T> target, BeanCopyCallBack<S, T> callBackFunction) {

        List<T> result = listType.get();
        for (S source : sources) {
            T t = target.get();
            copyProperties(source, t);
            result.add(t);
            if (callBackFunction != null) {
                callBackFunction.execute(source, t);
            }
        }
        return result;
    }

    /**
     * @param source
     * @param target
     * @param <S>
     * @param <T>
     * @return
     */
    public static <S, T> T copyProperties(S source, Supplier<T> target){

        Field[] declaredFields = source.getClass().getDeclaredFields();

        for (Field declaredField : declaredFields) {
            if (Object.class.isAssignableFrom(declaredField.getType())){
                System.out.println(1111);
            }
        }

        T t = target.get();
        copyProperties(source, t);
        return t;
    }

    /**
     * @param source
     * @param target
     * @param callBackFunction
     * @param <S>
     * @param <T>
     * @return
     */
    public static <S, T> T copyProperties(S source, Supplier<T> target, BeanCopyCallBack<S, T> callBackFunction){

        T t = copyProperties(source, target);
        if (callBackFunction != null){
            callBackFunction.execute(source, t);
        }
        return t;
    }

    /**
     *
     * @param bean
     * @return
     */
    public static Map<String, Object> beanToMap(Object bean) {
        return null == bean ? null : BeanMap.create(bean);
    }


    /**
     *
     * @param map
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T mapToBean(Map<String, Object> map, Class<T> clazz) {
        T bean = ClassUtils.newInstance(clazz);
        BeanMap.create(bean).putAll(map);
        return bean;
    }


    /**
     *
     * @param beans
     * @param <T>
     * @return
     */
    public static <T> List<Map<String, Object>> beansToMaps(List<T> beans) {
        return CollectionUtils.isEmpty(beans) ? Collections.emptyList() : beans.stream().map(BeanCopyUtils::beanToMap).collect(Collectors.toList());
    }

    /**
     *
     * @param maps
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> mapsToBeans(List<Map<String, Object>> maps, Class<T> clazz) {
        return CollectionUtils.isEmpty(maps) ? Collections.emptyList() : maps.stream().map((e) -> mapToBean(e, clazz)).collect(Collectors.toList());
    }





}
