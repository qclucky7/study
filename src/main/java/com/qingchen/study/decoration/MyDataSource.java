package com.qingchen.study.decoration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @ClassName MyDataSource
 * @description:
 * @author: WangChen
 * @create: 2020-04-23 14:52
 **/
public class MyDataSource implements DataSource{

    private String name;

    public MyDataSource(String name) {
        this.name = name;
    }

    @Override
    public String readData() {
        Path path = Paths.get(name);
        try {
            return new String(Files.readAllBytes(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void writeData(String data) {
        Path path = Paths.get(name);
        try {
            Files.write(path,data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
