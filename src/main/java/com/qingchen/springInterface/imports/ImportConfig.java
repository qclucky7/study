package com.qingchen.springInterface.imports;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @ClassName ImportConfig
 * @description:
 * @author: WangChen
 * @create: 2020-08-29 13:57
 **/
@Configuration
@Import(value = {ImportTest.class, ImprotTest02.class})
public class ImportConfig {
}
