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
    public static <S, T> Set<T> copySetProperties(Set<S> sources, Supplier<Set<T>> setType, Supplier<T> target) {
        return copySetProperties(sources, setType, target,null);
    }


    /**
     * @param sources
     * @param target
     * @param callBackFunction
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <S, T> Set<T> copySetProperties(Set<S> sources, Supplier<Set<T>> setType, Supplier<T> target, BeanCopyCallBack<S, T> callBackFunction) {

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
    public static <S, T> List<T> copyListProperties(List<S> sources, Supplier<List<T>> listType, Supplier<T> target, BeanCopyCallBack<S, T> callBackFunction) {

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


}
