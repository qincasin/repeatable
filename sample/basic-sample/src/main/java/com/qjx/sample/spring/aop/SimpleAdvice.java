package com.qjx.sample.spring.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * <Description>
 *
 * @author qinjiaxing on 2023/2/20
 * @author <others>
 */
public class SimpleAdvice implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("before");
        Object proceed = invocation.proceed();
        System.out.println("after");
        return proceed;
    }
}
