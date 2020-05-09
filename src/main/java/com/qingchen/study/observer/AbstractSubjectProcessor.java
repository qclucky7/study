package com.qingchen.study.observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName AbstractSubjectProcessor
 * @description:
 * @author: WangChen
 * @create: 2020-04-20 19:50
 **/
public abstract class AbstractSubjectProcessor {

    private Logger logger = LoggerFactory.getLogger(AbstractSubjectProcessor.class);

    protected static Map<Enum, IActionSubject> CACHE_MAP = new ConcurrentHashMap<>(32);
}
