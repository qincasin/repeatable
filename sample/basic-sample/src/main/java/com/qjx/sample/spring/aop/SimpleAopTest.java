package com.qjx.sample.spring.aop;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.RootClassFilter;

/**
 * <Description>
 *
 * @author qinjiaxing on 2023/2/20
 * @author <others>
 */
public class SimpleAopTest {

    public static void main(String[] args) {
        BeanOne one = new BeanOne();
        BeanTwo two = new BeanTwo();
        BeanOne proxyOne;
        BeanTwo proTwo;
        Pointcut pc = new SimpleStaticPointcut();
        Advice advice = new SimpleAdvice();
        Advisor advisor = new DefaultPointcutAdvisor(pc, advice);
        // 创建beanone 的代理
        ProxyFactory proxyFactory = new ProxyFactory();
        // 添加 通知
        proxyFactory.addAdvisor(advisor);
        // 设置目标对象
        proxyFactory.setTarget(one);
        proxyOne = (BeanOne) proxyFactory.getProxy();
        // 创建beantwo 的代理
        ProxyFactory proxyFactoryTwo = new ProxyFactory();
        proxyFactoryTwo.addAdvisor(advisor);
        proxyFactoryTwo.setTarget(two);
        proTwo = (BeanTwo) proxyFactoryTwo.getProxy();
        proxyOne.foo();
        proxyOne.bar();
        proTwo.foo();
        proTwo.bar();
    }

}
