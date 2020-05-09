package com.qingchen.study.observer;

import com.qingchen.study.observer.common.ActivitySubject;
import com.qingchen.study.observer.common.Vip1;
import com.qingchen.study.observer.common.Vip2;
import com.qingchen.study.observer.common.VipLevel;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.print.DocFlavor;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Component(value = "activitySubjectCommon")
public class ActivitySubjectCommon extends AbstractSubjectProcessor {

    @Resource
    private Vip1 vip1;
    @Resource
    private Vip2 vip2;

    //可以在注入后完成一些初始化的工作！！ 不错
    //Constructor >> @Autowired >> @PostConstruct
    @PostConstruct
    public void initData() {

        VipLevel[] values = VipLevel.values();
        for (VipLevel value : values) {
            CACHE_MAP.put(value, new ActivitySubject(value.getInitialCapacity()));
        }
        {
            IActionSubject iActionSubject = CACHE_MAP.get(VipLevel.VIP_LEVEL_5);
            iActionSubject.registerObserver(vip1);

        }
        {
            IActionSubject iActionSubject1 = CACHE_MAP.get(VipLevel.VIP_LEVEL_10);
            iActionSubject1.registerObserver(vip1);
            iActionSubject1.registerObserver(vip2);
        }

        System.out.println("CACHE_MAP = " + CACHE_MAP.toString());
        System.out.println(CACHE_MAP.get(VipLevel.VIP_LEVEL_5).notifyObservers(10));
        System.out.println(CACHE_MAP.get(VipLevel.VIP_LEVEL_10).notifyObservers(10));
    }

    @Test
    public void test() {
        System.out.println(CACHE_MAP.toString());
        IActionSubject iActionSubject = CACHE_MAP.get(VipLevel.VIP_LEVEL_10);
        List<String> list = iActionSubject.notifyObservers(10);
        System.out.println(list.toString());
    }

}
