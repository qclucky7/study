package com.qingchen.study.jvm;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * @ClassName MyBufferReadTest
 * @description:
 * @author: WangChen
 * @create: 2020-05-07 18:44
 **/
@RestController
public class MyBufferReadTest {


    @GetMapping("/buffer/{context}")
    public void myTest(@PathVariable("context") String context){

        try (BufferedReader bufferedReader
                     = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(context.getBytes()), StandardCharsets.UTF_8))
        ){

            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line);
            }
            System.out.println(stringBuilder.toString());
        } catch (Exception e){

        }

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(context.getBytes()), StandardCharsets.UTF_8));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line);
            }
            System.out.println(stringBuilder.toString());
        } catch (Exception e){

        }
    }
}
