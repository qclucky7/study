package com.qingchen.study.spring.myconfig;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @ClassName ImportSelector
 * @description:
 * @author: WangChen
 * @create: 2020-04-04 14:14
 **/
public class ImportSelect implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {

        return new String[]{"com.qingchen.study.spring.myconfig.User"};
    }
}
