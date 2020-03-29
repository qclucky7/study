package com.qingchen.study.strategy;

import com.sun.corba.se.spi.protocol.RequestDispatcherDefault;
import org.springframework.boot.autoconfigure.elasticsearch.jest.HttpClientConfigBuilderCustomizer;
import org.springframework.stereotype.Component;
import sun.misc.Request;
import sun.net.www.http.HttpClient;


/**
 *
 * @author : WangChen
 * @date : 2020/2/19 14:02
 */
@Component(value = "strategyContext")
public class StrategyContext {

    private Strategy strategy;


    /**
     * 借鉴建造者模式实现链式编程
     * @param strategy
     * @return StrategyContext
     */
    public StrategyContext setStrategy(Strategy strategy) {
        this.strategy = strategy;
        return this;
    }

    public void strategy(){
        strategy.doSomething();
    }

    public void strategy(int i){
        strategy.doSomething(i);
    }


}
