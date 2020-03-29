package com.qingchen.study.state;

/**
 * @InterfaceName behavior
 * @description:
 * @author: WangChen
 * @create: 2020-03-28 14:01
 **/
public interface Behavior {

    void order();

    void pay();

    void success();

    void take();
}
