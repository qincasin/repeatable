package com.qjx.sample.metrics;

import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.Metrics;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Value;

/**
 * 性能切面
 *
 * @author qinjiaxing on 2023/2/21
 * @author <others>
 */
@Slf4j
public class PerformanceMetricsAdvice implements MethodInterceptor {

    private static final String equals = "equals";

    private static final String hashcode = "hashCode";

    private static final String toString = "toString";

    // 缓存class的 simple name
    private static final ConcurrentHashMap<Class, String> cacheMap = new ConcurrentHashMap<>();

    // summary map 锁
    private Object summaryMutex = new Object();

    // 缓存每个方法对应的 DistributionSummary
    private static Map<String, DistributionSummary> summaryMap = new HashMap<>();


    // metric name
    private static String metric_summary = "qjx.service.performance.ms";

    // top 90 每个区间的范围，单位 ms
    @Value("${qjx.service.performance.sla:20,50,100,500,1000}")
    private long[] sla;


    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Method methodName = invocation.getMethod();
        // 特殊的一些方法直接放行
        if (isSpecialMethod(invocation.getMethod())) {
            return invocation.proceed();
        }
        Object result;
        // 开始时间
        long start = System.nanoTime();
        Class<?> classTarget = invocation.getThis().getClass();
        try {
            result = invocation.proceed();
        } catch (Throwable e) {
            throw e;
        } finally {
            // 计算相关的指标信息
            try {
                long spendNanoTime = System.nanoTime() - start;
                String classSimpleNameWithDot = cacheMap.computeIfAbsent(classTarget, k -> k.getSimpleName() + ".");
                String fullMethodName = (classSimpleNameWithDot + methodName.getName()).intern();
                PerformanceMetrics performanceMetrics = classTarget.getAnnotation(PerformanceMetrics.class);
                this.printPerformanceWarnLog(performanceMetrics, spendNanoTime, fullMethodName, invocation.getArguments());
                // 转成s 维持metrics 指标名称不变
                double costSecond = (double) spendNanoTime / 1_000_000.0;
                DistributionSummary sCache = summaryMap.get(fullMethodName);
                if (sCache != null) {
                    sCache.record(costSecond);
                } else {
                    synchronized (summaryMutex) {
                        sCache = summaryMap.get(fullMethodName);
                        if (sCache == null) {
                            DistributionSummary builder = DistributionSummary.builder(metric_summary)
                                    .tags("appName", "basic-app", "method", fullMethodName)
                                    .publishPercentileHistogram(false)
                                    .sla(sla)
                                    .register(Metrics.globalRegistry);
                            summaryMap.put(fullMethodName, builder);
                            builder.record(costSecond);
                        } else {
                            sCache.record(costSecond);
                        }
                    }
                }

            } catch (Exception e) {
                log.warn("PerformanceMetricsAdvice exception:{}", e.getMessage(), e);
            }
        }
        return result;
    }

    /**
     * 记录性能日志
     *
     * @param performanceMetrics
     * @param spendNanoTime
     * @param fullMethodName
     * @param arguments
     */
    private void printPerformanceWarnLog(PerformanceMetrics performanceMetrics, long spendNanoTime, String fullMethodName, Object[] arguments) {
        if (performanceMetrics == null || performanceMetrics.performanceThreshold() == Long.MAX_VALUE) {
            return;
        }
        long costMs = TimeUnit.MILLISECONDS.convert(spendNanoTime, TimeUnit.NANOSECONDS);
        if (costMs < performanceMetrics.performanceThreshold()) {
            return;
        }
        if (performanceMetrics.printErrorLog()) {
            log.error("执行方法{}耗时:{}MS,入参:{}", fullMethodName, costMs, Arrays.toString(arguments));
        } else {
            log.warn("执行方法{}耗时:{}MS,入参:{}", fullMethodName, costMs, Arrays.toString(arguments));
        }
    }

    private boolean isSpecialMethod(Method method) {
        return hashcode.equals(method.getName()) || equals.equals(method.getName()) || toString.equals(method.getName());
    }
}
