package com.qingchen.study.ratelimit;

import com.qingchen.study.globalexception.Result;

/**
 * @ClassName DefaultRateLimitResult
 * @description:
 * @author: WangChen
 * @create: 2020-06-19 09:00
 **/
public class DefaultRateLimitResult extends AbstractRateLimitAdapter<Result>{


    @Override
    public Result getLimitResource(Object result, boolean limit) {
        if (limit){
            return Result.ofFail(403, "请求限流自定义返回类型");
        }
        return null;
    }
}
