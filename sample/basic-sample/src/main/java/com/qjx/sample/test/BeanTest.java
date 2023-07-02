package com.qjx.sample.test;

import com.qjx.sample.entity.UserTest;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <Description>
 *
 * @author qinjiaxing on 2023/2/23
 * @author <others>
 */
@Configuration
@ConditionalOnProperty(name = "bean.user.enable",havingValue = "true")
public class BeanTest {
    @Bean
    // @ConditionalOnMissingBean
    public UserTest user(){
        return new UserTest();
    }

}
