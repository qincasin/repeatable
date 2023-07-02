package com.qjx.sample.spring.startup;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <Description>
 *
 * @author qinjiaxing on 2023/5/18
 * @author <others>
 */
@Configuration
public class TestImport {
    @Bean
    public TestString testString(){
        return new TestString();
    }

    @Data
    public static class TestString{

        public TestString() {
            System.out.println("TestString .....");
        }
    }
}
