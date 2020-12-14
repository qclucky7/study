package com.qingchen.springInterface.scan;

import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;

import java.io.IOException;
import java.util.Set;

/**
 * @ClassName PackageScan
 * @description:
 * @author: WangChen
 * @create: 2020-10-14 16:10
 **/
public class PackageScan implements ResourceLoaderAware {

    private ResourceLoader resourceLoader;

    @Test
    public void test() throws IOException {

        ResourcePatternResolver resolver = ResourcePatternUtils.getResourcePatternResolver(resourceLoader);
        Resource[] resources = resolver.getResources("classpath*:com/qingchen/**/*.class");

        for (Resource resource : resources) {
            //System.out.println(resource.getFilename());
        }


        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(true);
        Set<BeanDefinition> beanDefinitions = provider.findCandidateComponents("com/qingchen/study");
        for(BeanDefinition beanDefinition : beanDefinitions) {
            System.out.println(beanDefinition.getBeanClassName()
                    + "\t" + beanDefinition.getResourceDescription()
                    + "\t" + beanDefinition.getClass());
        }

    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}
