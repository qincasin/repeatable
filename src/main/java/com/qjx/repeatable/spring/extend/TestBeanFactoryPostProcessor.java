package com.qjx.repeatable.spring.extend;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * 这个接口是 beanFactory 的扩展接口，调用时机在spring在读取 beanDefinition 信息之后，实例化bean之前。
 * 在这个时机，用户可以通过实现这个扩展接口来自行处理一些东西，比如修改已经注册的 beanDefinition 的元信息。
 *
 */
public class TestBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("3. [BeanFactoryPostProcessor] --> postProcessBeanFactory");
    }
}
