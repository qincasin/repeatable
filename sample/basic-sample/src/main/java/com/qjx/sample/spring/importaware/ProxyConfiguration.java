package com.qjx.sample.spring.importaware;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportAware;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

/**
 * <Description>
 *
 * @author qinjiaxing on 2023/2/20
 * @author <others>
 */
@Configuration
public class ProxyConfiguration implements ImportAware {

    private AnnotationAttributes attributes;

    @Bean
    public ProxyMode proxyMode() {
        ProxyMode p = new ProxyMode();
        p.setMode(attributes.getString("mode"));
        return p;
    }

    @Override
    public void setImportMetadata(AnnotationMetadata importMetadata) {
        this.attributes = AnnotationAttributes.fromMap(importMetadata.getAnnotationAttributes(EnableProxy.class.getName(), false));

    }
}
