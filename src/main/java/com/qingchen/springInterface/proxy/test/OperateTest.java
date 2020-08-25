package com.qingchen.springInterface.proxy.test;

import com.qingchen.springInterface.proxy.RoutingInjected;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName OperateTest
 * @description:
 * @author: WangChen
 * @create: 2020-07-16 11:04
 **/
@RestController
public class OperateTest {

    public static ThreadLocal<String> versions =  ThreadLocal.withInitial(()-> "1.0");

    @RoutingInjected
    private IOperate operate;


    @GetMapping("/routing")
    public void test(@RequestParam("version") String version){
        versions.set(version);

        operate.operate();
    }

}
