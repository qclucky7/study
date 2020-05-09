package com.qingchen.study.prototype;

import org.junit.Test;

/**
 * @ClassName ProtoTypeTest
 * @description:
 * @author: WangChen
 * @create: 2020-04-23 13:41
 **/
public class ProtoTypeTest {


    @Test
    public void MyTest() throws Exception{

        ProtoType protoType = new ProtoType();
        protoType.setId(1);
        protoType.setQuote(new Quote(2, "王晨"));


        /**
         @浅拷贝
        ProtoType{id=1, quote=Quote{quoteId=2, name='李四'}}
        ProtoType{id=100000, quote=Quote{quoteId=2, name='李四'}}
        ProtoType{id=500, quote=Quote{quoteId=2, name='李四'}}
        不是基本类型的话会覆盖原来的值！

         @Override
         protected ProtoType clone() throws CloneNotSupportedException {
         ProtoType clone = (ProtoType)super.clone();
         clone.setQuote(getQuote().clone());
         return clone;
         }

         @深拷贝
        * {@link ProtoType}
        */

        ProtoType clone = protoType.clone();
        ProtoType clone1 = protoType.clone();

        clone.getQuote().setName("法外狂徒张三！");
        clone.setId(100000);

        clone1.getQuote().setName("李四");
        clone1.setId(500);

        System.out.println(protoType.toString());
        System.out.println(clone.toString());
        System.out.println(clone1.toString());

        System.out.println(clone.getQuote().equals(clone1.getQuote()));




    }
}
