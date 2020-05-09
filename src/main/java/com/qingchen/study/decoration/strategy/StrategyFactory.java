package com.qingchen.study.decoration.strategy;

/**
 * @ClassName StrategyFactory
 * @description:
 * @author: WangChen
 * @create: 2020-04-23 16:27
 **/
public class StrategyFactory {

//    private static class StrategyFactoryProvider{
//         private static StrategyFactory instance = new StrategyFactory();
//    }
//
//    public StrategyFactory getInstance(){
//         return StrategyFactoryProvider.instance;
//    }

    public static EncryptionStrategy getStrategy(Class<? extends EncryptionStrategy> clazz){
        try {
           return clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }


}
