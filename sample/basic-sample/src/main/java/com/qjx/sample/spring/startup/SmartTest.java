package com.qjx.sample.spring.startup;

import java.util.concurrent.TimeUnit;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * <Description>
 *
 * @author qinjiaxing on 2023/5/18
 * @author <others>
 */
@Configuration
@Slf4j
@Lazy
public class SmartTest implements SmartInitializingSingleton, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterSingletonsInstantiated() {
        log.info("SmartTest start ");
        SmartTestBean smartTestBean = new SmartTestBean();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        smartTestBean.setName("zhangsan");
        log.info("SmartTest end ");
        AutowireCapableBeanFactory beanFactory = applicationContext.getAutowireCapableBeanFactory();
        ((DefaultListableBeanFactory) beanFactory).registerSingleton("smartTestBean", smartTestBean);
    }

    @Data
    public static class SmartTestBean {

        private String name;
    }
}
