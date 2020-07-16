package com.qingchen.springInterface.proxy;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName OperateTest
 * @description:
 * @author: WangChen
 * @create: 2020-07-16 11:04
 **/
@RestController
public class OperateTest {

    @RoutingInjected
    private IOperate operate;


    @GetMapping("/routing")
    private void test(){
        operate.operate();
    }

}
