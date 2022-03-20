package com.qjx.external.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestImportConfig {
    @Bean
    public TestImportA testImportA(){
        return new TestImportA();
    }

    @Bean
    public TestImportB testImportB(){
        return new TestImportB();
    }

}
