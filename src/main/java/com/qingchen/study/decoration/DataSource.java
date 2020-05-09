package com.qingchen.study.decoration;

/**
 * @InterfaceName DataSource
 * @description:
 * @author: WangChen
 * @create: 2020-04-23 14:51
 **/
public interface DataSource {

    String readData();

    void writeData(String data);
}
