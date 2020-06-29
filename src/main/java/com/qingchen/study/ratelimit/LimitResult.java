package com.qingchen.study.ratelimit;

/**
 * @InterfaceName LimitResult
 * @description:
 * @author: WangChen
 * @create: 2020-06-19 08:47
 **/
public interface LimitResult<T> {

    Object getLimitResource(Object obj, boolean limit);

}
