package com.qingchen.study.decoration;

import com.qingchen.study.decoration.strategy.EncryptionStrategy;

/**
 * @ClassName DataSourceEncryption
 * @description:
 * @author: WangChen
 * @create: 2020-04-23 15:06
 **/
public class DataSourceEncryption extends AbstractDataSourceDecorator {

    private EncryptionStrategy encryptionStrategy;

    //这初始化必须先初始化父类!!
    public DataSourceEncryption(EncryptionStrategy encryptionStrategy, DataSource dataSource) {
        super(dataSource);
        this.encryptionStrategy = encryptionStrategy;
    }

    @Override
    public String readData() {
        return encryptionStrategy.decrypt(super.readData());
    }

    @Override
    public void writeData(String data) {
        super.writeData(encryptionStrategy.encrypt(data));
    }
}
