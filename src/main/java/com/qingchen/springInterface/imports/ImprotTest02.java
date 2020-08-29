package com.qingchen.springInterface.imports;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @ClassName ImprotTest02
 * @description:
 * @author: WangChen
 * @create: 2020-08-29 14:28
 **/
public class ImprotTest02 implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        System.out.println(annotationMetadata.toString());
    }
}
