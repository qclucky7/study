package com.qingchen.study.decoration;

import com.qingchen.study.decoration.strategy.Base64Strategy;
import com.qingchen.study.decoration.strategy.StrategyEnum;
import com.qingchen.study.decoration.strategy.StrategyFactory;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @ClassName DecorationTest
 * @description:
 * @author: WangChen
 * @create: 2020-04-23 15:10
 **/
public class DecorationTest {

    @Test
    public void myTest() throws Exception{
        DataSource dataSourceEncryption = new DataSourceEncryption(
                StrategyEnum.BASE64,
                new MyDataSource("D:\\test.txt")
                );
        Path path = Paths.get("D:\\my.txt");
        Path path1 = Paths.get("D:\\my1.txt");
       Files.lines(path, StandardCharsets.UTF_8).forEach(line ->{
            try {
                Files.write(path1, line.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        dataSourceEncryption.writeData(new String("111111"));
        System.out.println(dataSourceEncryption.readData());

    }
}
