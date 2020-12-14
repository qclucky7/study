package com.qingchen;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.junit.Test;

/**
 * @ClassName User
 * @description:
 * @author: WangChen
 * @create: 2020-09-02 15:11
 **/
@Data
public class LombokTest {

    private String name;
    private Integer phone;

    @Test
    public void test(){
        A a = new A();
        a.setName("123");
        a.setAge("456");
        a.setAddress("111111");

        String s = JSON.toJSONString(a);

        System.out.println(s);

        B b = JSON.parseObject(s, B.class);

        System.out.println(b);
    }


    static class A{
        private String name;
        private String age;
        private String address;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }


    static class B{
        private String name;
        private String age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "B{" +
                    "name='" + name + '\'' +
                    ", age='" + age + '\'' +
                    '}';
        }
    }


}
