package com.qjx.repeatable.spring.extend;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

/**
 *   这个时候的spring 容器还没有被初始化，所以该扩展点的生效方式有三个
 *   springApplication.addInitializers(new TestApplicationContextInitializer())
 *   context.initializer.classes=com.qjx.repeatable.spring.extend.TestApplicationContextInitializer
 *   org.springframework.context.ApplicationContextInitializer=com.qjx.repeatable.spring.extend.TestApplicationContextInitializer
 */
public class TestApplicationContextInitializer implements ApplicationContextInitializer {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        System.out.println("1. TestApplicationContextInitializer !~~~~~~~");
    }
}
