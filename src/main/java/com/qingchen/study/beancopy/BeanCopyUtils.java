package com.qingchen.study.beancopy;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.*;
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
     * 集合数据的拷贝
     * @param sources
     * @param target
     * @return
     */
    public static <S, T> Set<T> copySetProperties(Set<S> sources,Supplier<Set> setType, Supplier<T> target) {
        return copySetProperties(sources, setType, target,null);
    }


    /**
     * 带回调函数的集合数据的拷贝（可自定义字段拷贝规则）
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
     * 集合数据的拷贝
     * @param sources
     * @param target
     * @return
     */
    public static <S, T> List<T> copyListProperties(List<S> sources, Supplier<T> target) {
        return copyListProperties(sources, ArrayList::new ,target, null);
    }

    /**
     * 默认ArrayList copy
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
     * 带回调函数的集合数据的拷贝（可自定义字段拷贝规则）
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
     * 简单拷贝
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
     * 回调拷贝
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


    private static Field[] getAllFields(Object o){
        Class c= o.getClass();
        List<Field> fieldList = new ArrayList<>();
        while (c!= null){
            fieldList.addAll(new ArrayList<>(Arrays.asList(c.getDeclaredFields())));
            c= c.getSuperclass();
        }
        Field[] fields = new Field[fieldList.size()];
        fieldList.toArray(fields);
        return fields;
    }


    /**
     * 反射获取对象中的list数据
     * @param object
     * @param <T>
     */
    public static<T> void getList(Object object){
        List<T> resultList = new ArrayList<>();

            Field[] fields = getAllFields(object);
            Field[] filterList= filterField(fields);
            System.out.println(Arrays.toString(filterList));
            Arrays.stream(filterList).forEach(var -> {
                //List集合
                if(List.class.isAssignableFrom(var.getType())){
                    Type type = var.getGenericType();
                    if(type instanceof ParameterizedType){
                        if(!var.isAccessible()){
                            var.setAccessible(true);
                        }
                        //获取到属性值的字节码
                        try {
                            Class<?> clzz = var.get(object).getClass();
                            //反射调用获取到list的size方法来获取到集合的大小
                            Method sizeMethod = clzz.getDeclaredMethod("size");
                            if(!sizeMethod.isAccessible()){
                                sizeMethod.setAccessible(true);
                            }
                            //集合长度
                            int size = (int) sizeMethod.invoke(var.get(object));
                            System.out.println(size);
                            //循环遍历获取到数据
                            for (int i = 0; i < size; i++) {
                                //反射获取到list的get方法
                                Method getMethod = clzz.getDeclaredMethod("get", int.class);
                                //调用get方法获取数据
                                if(!getMethod.isAccessible()){
                                    getMethod.setAccessible(true);
                                }
                                T var1 = (T) getMethod.invoke(var.get(object), i);
                                resultList.add(var1);
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

            System.out.println(resultList.toString());
    }


    /**
     * 过滤字段
     * @param
     * @return
     */
    public static Field[] filterField(Field[] fields){
        List<Field> tempList = Arrays.stream(fields).filter(field -> null != field
                && !Modifier.isFinal(field.getModifiers())
                && !Modifier.isStatic(field.getModifiers())
                && !Modifier.isAbstract(field.getModifiers())).collect(Collectors.toList());

        int arrLength = CollectionUtils.isEmpty(tempList) ? 1:tempList.size();

        Field[] resultArr = new Field[arrLength];
        if(!CollectionUtils.isEmpty(tempList)){
            tempList.toArray(resultArr);
        }
        return resultArr;
    }



}
