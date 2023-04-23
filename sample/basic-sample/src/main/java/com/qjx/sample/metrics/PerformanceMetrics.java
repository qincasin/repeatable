package com.qjx.sample.metrics;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 性能监控数据收集
 * 使用注解后，所在的类的方法执行时间和次数将被收集到prometheus中
 *
 * @author qinjiaxing on 2023/2/21
 * @author <others>
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PerformanceMetrics {

    /**
     * 性能阈值（MS），执行时间达到阈值则打印日志（WARN或者ERROR）
     *
     * @return the long 毫秒3
     */
    long performanceThreshold() default Long.MAX_VALUE;

    /**
     * 为true时打印ERROR日志，否则打印WARN日志
     *
     * @return the boolean
     */
    boolean printErrorLog() default false;
}
