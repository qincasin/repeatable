package com.qjx.sample.metrics;

import java.lang.reflect.Method;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

/**
 * <Description>
 *
 * @author qinjiaxing on 2023/2/20
 * @author <others>
 */
public class PerformanceMetricsPointcut extends StaticMethodMatcherPointcut {

    /**
     * 静态切入点，就是说spring会针对目标上的每一个方法调用一次MethodMatcher的matches方法，
     * 其返回值被缓冲起来以便日后使用
     * <p>
     * 这样，对每一个方法的适用性测试只会进行一次，相对动态的效率比较高，推荐使用
     *
     * @param method
     * @param targetClass
     * @return
     */
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return false;
    }
}
