package com.qingchen.demo.mydemo.controller;

import com.qingchen.demo.mydemo.model.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.junit.Test;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * @ClassName QueueTest
 * @description:  localhost:port/swagger-ui.html
 * @author: WangChen
 * @create: 2020-04-26 12:23
 **/
@RestController
@Api(value = "swagger!")
@RequestMapping("/test")
public class QueueTestController {

    /**
     * 属性名称                数据类型              默认值      说明
     * name                    String               “”       参数名称(实体类字段名称)
     * value                   String               “”       参数简要说明
     * defaultValue            String               “”       描述参数的默认值
     * allowableValues         String               “”       限制此参数接收的值，可使用的值或值得范围
     * required                boolean              false    指定是否为必填参数,false:非必传参数; true:必传参数
     * access                  String               ""       参数过滤，参考: io.swagger.core.filter.SwaggerSpecFilte
     * allowMultiple           boolean              false    指定参数是否可以通过多次出现来接收多个值
     * dataType                String               “”       参数的数据类型，可以使类名或原始数据类型
     * dataTypeClass           Class<?>            Void.class 参数的类，如果提供则覆盖 dataType
     * paramType               String               “”        参数的参数类型，有效值为 path, query, body, header, form
     * example                 String               “”        非请求体(body)参数的单个请求示例
     * examples                Example           @Example(value = @ExampleProperty(mediaType = “”, value = “”)) 参数示例，仅适用于 BodyParameters(请求体类型的)
     * type                    String               “”        添加覆盖检测到的类型的功能
     * format                  String               “”        添加提供自定义格式的功能
     * allowEmptyValue         boolean              false     添加将 format 设置为空的功能
     * readOnly                boolean              false     添加被指定为只读的能力
     * collectionFormat        String               “”        添加使用 array 类型 collectionFormat 的功能
     * @param id
     * @param message
     * @return
     */
    @ApiOperation("方法上！")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id描述", required = true),
            @ApiImplicitParam(
                    name = "message",
                    value = "message描述",
                    required = true,
                    dataTypeClass = Message.class)
    })

    @GetMapping("/queue/{id}")
    public String myTest(@PathVariable("id") int id){
        System.out.println("执行了！！！");
        return "返回消息内容";
    }

    @Test
    public void testClone(){
        long l = System.currentTimeMillis();
        for (int i = 0; i < 300 ; i++) {
            Message.getMessage()
                    .setId(0)
                    .setUrl("url")
                    .setContext("context")
                    .setLocalDateTime(LocalDateTime.now());
        }
        System.out.println("clone = " + (System.currentTimeMillis() - l));

        long l1 = System.currentTimeMillis();
        for (int i = 0; i < 300 ; i++) {
            new Message()
                    .setId(0)
                    .setUrl("url")
                    .setContext("context")
                    .setLocalDateTime(LocalDateTime.now());
        }
        System.out.println("new =" + (System.currentTimeMillis() - l1));

    }
}
