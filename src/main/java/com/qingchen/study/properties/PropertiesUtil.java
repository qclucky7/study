package com.qingchen.study.properties;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Properties;

import static org.apache.ibatis.io.Resources.getDefaultClassLoader;
import static org.apache.ibatis.io.Resources.getResourceAsStream;

/**
 * @ClassName PropertiesUtil
 * @description:
 * @author: WangChen
 * @create: 2020-04-29 14:45
 **/
public class PropertiesUtil {

    private PropertiesUtil() {
    }

    public static Properties getProperties(String path) {
        if (StringUtils.isBlank(path)){
            return null;
        }
        try (
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(
                                PropertiesUtil.class.getClassLoader().getResourceAsStream(path)
                               //getResourceAsStream(getDefaultClassLoader(), path)
                        )
                )
        ) {
            Properties properties = new Properties();
            properties.load(bufferedReader);
            return properties;
        } catch (Exception e) {

        }
        return null;
    }

    public static String getProperty(String path, String key){
        Properties properties = getProperties(path);
        if (properties == null) {
            return "";
        }
        return properties.getProperty(key);
    }

    public static void main(String[] args) {
        Properties properties = PropertiesUtil.getProperties("application.properties");
        String nickname = properties.getProperty("nickname");
        System.out.println(nickname);
    }
}
