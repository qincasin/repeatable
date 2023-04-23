package com.qjx.sample.metrics;

import com.qjx.sample.metrics.service.ApplicationService;
import com.qjx.sample.metrics.service.DomainService;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Set;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.stereotype.Service;

/**
 * <Description>
 *
 * @author qinjiaxing on 2023/2/20
 * @author <others>
 */
public class PerformanceMetricsPointcut extends StaticMethodMatcherPointcut implements ClassFilter {

    private Set<String> basePackages;

    public PerformanceMetricsPointcut(Set<String> basePackages) {
        this.basePackages = basePackages;
        this.setClassFilter(this);
    }

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
        // 仅仅统计public 方法
        if (!Modifier.isPublic(method.getModifiers())) {
            return false;
        }
        return true;
    }

    @Override
    public boolean matches(Class<?> clazz) {
        //匹配指定的包路径前缀
        if (!include(clazz.getName())) {
            return false;
        }
        // 使用了@PerformanceMetrics
        if (clazz.isAnnotationPresent(PerformanceMetrics.class)) {
            return true;
        }
        // 使用了  ApplicationService 或者 DomainService
        boolean flag = ApplicationService.class.isAssignableFrom(clazz) || DomainService.class.isAssignableFrom(clazz);
        if (clazz.isAnnotationPresent(Service.class) && flag) {
            return true;
        }
        return false;
    }

    private boolean include(String name) {
        if (basePackages != null) {
            for (String basePackage : basePackages) {
                if (name.startsWith(basePackage)) {
                    return true;
                }
            }
        }
        return false;
    }
}
