package com.qingchen.study.spring.myconfig;

import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

/**
 * @ClassName MyFilterType
 * @description: 这个配合componentSacn里面的自定义filter.type类型实现不同的注册逻辑
 * @author: WangChen
 * @create: 2020-04-04 13:37
 **/
public class MyFilterType implements TypeFilter {

    /**
     * @param metadataReader 读取到当前正在扫描的类的信息
     * @param metadataReaderFactory 可以获得任何其他类的信息
     * @return
     * @throws IOException
     */
    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {

        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        Resource resource = metadataReader.getResource();
        return false;
    }
}
