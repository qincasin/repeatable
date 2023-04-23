package com.qjx.sample.metrics;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;

/**
 * 拦截需要统计性能的方法，并执行拦截器
 *
 * @author qinjiaxing on 2023/2/21
 * @author <others>
 */
public class PerformanceMetricsAdvisor extends AbstractPointcutAdvisor {

    private Pointcut pointcut;

    private Advice advice;

    public PerformanceMetricsAdvisor(Pointcut pointcut, Advice advice) {
        this.pointcut = pointcut;
        this.advice = advice;
    }

    @Override
    public Pointcut getPointcut() {
        return pointcut;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }
}
