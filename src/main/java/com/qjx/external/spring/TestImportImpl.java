package com.qjx.external.spring;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.core.type.AnnotationMetadata;

public class TestImportImpl implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        MergedAnnotations annotations = importingClassMetadata.getAnnotations();
        MergedAnnotation<EnableTestImport> enableTestImportMergedAnnotation = annotations.get(EnableTestImport.class);
        System.out.println("enableTestImportMergedAnnotation   ~~~~~ "+enableTestImportMergedAnnotation.getString("value"));
        return new String[]{"com.qjx.external.spring.TestImportA","com.qjx.external.spring.TestImportB"};
    }
}
