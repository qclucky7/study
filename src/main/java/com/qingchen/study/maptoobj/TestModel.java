package com.qingchen.study.maptoobj;

/**
 * @author WangChen
 * @since 2020-11-30 10:56
 **/
public class TestModel extends TeaModel{

    @NameInMap(value = "my_name")
    private String name;

    @NameInMap(value = "my_age")
    private Long age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "TestModel{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
