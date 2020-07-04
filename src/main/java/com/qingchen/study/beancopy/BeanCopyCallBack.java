package com.qingchen.study.beancopy;

/**
 * @InterfaceName BeanCopyUtilCallBack
 * @description:
 * @author: WangChen
 * @create: 2020-06-29 10:16
 **/
@FunctionalInterface
public interface BeanCopyCallBack<S, T> {

    /**
     * 定义默认回调方法
     * @param t
     * @param s
     */
    void execute(S t, T s);


}
