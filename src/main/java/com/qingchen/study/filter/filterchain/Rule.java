package com.qingchen.study.filter.filterchain;

/**
 * @ClassName Rule
 * @description:
 * @author: WangChen
 * @create: 2020-03-28 16:24
 **/
public class Rule {

    private String name;

    private long age;

    public Rule(String name, long age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Rule{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
