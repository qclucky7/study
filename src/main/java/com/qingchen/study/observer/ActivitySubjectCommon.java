package com.qingchen.study.observer;

import com.qingchen.study.observer.common.ActivitySubject;
import com.qingchen.study.observer.common.Vip1;
import com.qingchen.study.observer.common.Vip2;
import com.qingchen.study.observer.common.VipLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Component(value = "activitySubjectCommon")
public class ActivitySubjectCommon {
    private Logger logger = LoggerFactory.getLogger(ActivitySubjectCommon.class);

    private static final Map<VipLevel, IActionSubject> CACHE_MAP = new ConcurrentHashMap<>();

    @Resource
    private Vip1 vip1;
    @Resource
    private Vip2 vip2;

    static {
        CACHE_MAP.put(VipLevel.VIP_LEVEL_5, new ActivitySubject());
        CACHE_MAP.put(VipLevel.VIP_LEVEL_10, new ActivitySubject());
    }


    private void init(){
        IActionSubject iActionSubject = CACHE_MAP.get(VipLevel.VIP_LEVEL_5);
        iActionSubject.registerObserver(vip1);
        IActionSubject iActionSubject1 = CACHE_MAP.get(VipLevel.VIP_LEVEL_10);
        iActionSubject1.registerObserver(vip1);
        iActionSubject1.registerObserver(vip2);
    }


    public void test(int i){
        init();
        IActionSubject iActionSubject = CACHE_MAP.get(VipLevel.VIP_LEVEL_10);
        List<String> list = iActionSubject.notifyObservers(10);
        System.out.println(list.toString());
    }

}
