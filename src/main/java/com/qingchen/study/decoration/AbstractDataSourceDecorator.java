package com.qingchen.study.decoration;

/**
 * @ClassName AbstractDataSourceDecorator
 * @description:
 * @author: WangChen
 * @create: 2020-04-23 15:04
 **/
public abstract class AbstractDataSourceDecorator implements DataSource{

    private DataSource dataSource;

    public AbstractDataSourceDecorator(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public String readData() {
       return dataSource.readData();
    }

    @Override
    public void writeData(String data) {
        dataSource.writeData(data);
    }
}
