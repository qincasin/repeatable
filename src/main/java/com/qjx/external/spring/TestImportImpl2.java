package com.qjx.external.spring;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.core.type.AnnotationMetadata;

public class TestImportImpl2 implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        MergedAnnotations annotations = importingClassMetadata.getAnnotations();
        MergedAnnotation<EnableTestImport2> enableTestImport2MergedAnnotation = annotations.get(
                EnableTestImport2.class);
        System.out.println("enableTestImport2MergedAnnotation22222 ~~~~ "+enableTestImport2MergedAnnotation.getString("value"));
        registry.registerBeanDefinition("testImportA",new RootBeanDefinition(TestImportA.class));
        registry.registerBeanDefinition("testImportB",new RootBeanDefinition(TestImportB.class));
    }
}
