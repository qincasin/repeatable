package com.qjx.sample.spring.aop;

import java.lang.reflect.Method;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

/**
 * <Description>
 *
 * @author qinjiaxing on 2023/2/20
 * @author <others>
 */
public class SimpleStaticPointcut extends StaticMethodMatcherPointcut {

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return ("foo".equals(method.getName()));
    }

    public ClassFilter getClassFilter() {
        return new ClassFilter() {
            @Override
            public boolean matches(Class<?> clazz) {
                return clazz == BeanOne.class;
            }
        };
    }
}
