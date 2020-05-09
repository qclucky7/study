package com.qingchen.study.mydatasource;

import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * @ClassName DynamicDataSource
 * @description:
 * @author: WangChen
 * @create: 2020-05-04 14:45
 **/

public class DynamicDataSource extends AbstractRoutingDataSource {
    /**
     * 列出所有的数据源key（常用数据库名称来命名）
     * 注意：
     * 1）这里数据源与数据库是一对一的
     */
    public enum DatabaseType {
        //主库
        dataSourceMaster,
        //从库
        dataSourceSlave
    }

    private static ThreadLocal<DatabaseType> contextHolder = new ThreadLocal<>();

    public static void setDataBaseType(DatabaseType type){
        contextHolder.set(type);
    }

    public static DatabaseType getDataBaseType(){
        DatabaseType db = contextHolder.get();
        if (db == null) {
            System.out.println("db为空！");
            db = DatabaseType.dataSourceMaster;
        }
        return db;
    }

    @Override
    public Object determineCurrentLookupKey() {
        return getDataBaseType();
    }

    /**
     * 清理链接类型
     */
    public static void clearDbType() {
        contextHolder.remove();
    }

}
