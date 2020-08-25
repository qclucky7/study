package com.qingchen.study.reflection;

/**
 * @ClassName ReflectionObject
 * @description:
 * @author: WangChen
 * @create: 2020-08-19 09:58
 **/
public class ReflectionObject {

    public String str;
    private Integer integer;
    private String msg;

    private final String FINAL_VALUE = "FINAL_VALUE";

    public ReflectionObject() {
    }

    private ReflectionObject(String str) {
        this.str = str;
    }

    public String getFinalValue(){
        return FINAL_VALUE;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public Integer getInteger() {
        return integer;
    }

    public void setInteger(Integer integer) {
        this.integer = integer;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void test01(){

    }

    public Integer test02(Integer integer){

        return integer;
    }

    private Integer test03(String str, String str2){
        System.out.println(str);
        System.out.println(str2);
        return Integer.parseInt(str) + Integer.parseInt(str2);
    }

    @Override
    public String toString() {
        return "ReflectionObject{" +
                "str='" + str + '\'' +
                ", integer=" + integer +
                ", msg='" + msg + '\'' +
                ", FINAL_VALUE='" + FINAL_VALUE + '\'' +
                '}';
    }
}
