package com.qjx.sample.metrics;

import com.qjx.sample.entity.UserTest;
import java.util.HashSet;
import java.util.Set;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <Description>
 *
 * @author qinjiaxing on 2023/2/21
 * @author <others>
 */
@Configuration
@AutoConfigureOrder(-1)
@ConditionalOnProperty(prefix = "qjx.base.metrics.enable", name = "enabled", havingValue = "true", matchIfMissing = true)
@ConditionalOnExpression(value = "${base.metrics.enabled} || ${qjx.base.metrics.enabled}")
public class PerformanceMetricsAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(name = "user")
    public UserTest user(){
        return new UserTest();
    }


    @Bean
    @ConfigurationProperties(prefix = "base.metrics")
    public  MetricProperties metricProperties(){
        return new MetricProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "qjx.base.metrics")
    public  MetricProperties metricProperties2(){
        return new MetricProperties();
    }


    @Bean
    @ConditionalOnMissingBean
    public PerformanceMetricsAdvice performanceMetricsInterceptor() {
        return new PerformanceMetricsAdvice();
    }

    @Bean
    @ConditionalOnMissingBean
    public PerformanceMetricsPointcut performanceMetricsPointcut() {
        return new PerformanceMetricsPointcut(getBasePackage());
    }

    @Bean
    @ConditionalOnMissingBean
    public PerformanceMetricsAdvisor performanceMetricsAdvisor(PerformanceMetricsPointcut performanceMetricsPointcut,
                                                               PerformanceMetricsAdvice performanceMetricsAdvice) {
        return new PerformanceMetricsAdvisor(performanceMetricsPointcut, performanceMetricsAdvice);
    }

    private Set<String> getBasePackage() {
        Set<String> set = new HashSet<>();
        set.add("com.qjx");
        return set;
    }

}
