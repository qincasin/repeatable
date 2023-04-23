package com.qjx.sample.invoke.mark;

import java.util.List;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

/**
 * <Description>
 *
 * @author qinjiaxing on 2023/4/23
 * @author <others>
 */
@Configuration
@ConditionalOnProperty(value = MarkConfiguration.USDK_MARK_PREFIX + ".enabled", havingValue = "true", matchIfMissing = true)
public class MarkConfiguration {

    public static final String USDK_MARK_PREFIX = "usdk.mark";

    @Bean
    @ConditionalOnProperty
    public FeignRequestMarkEnhancer feignRequestMarkEnhancer(ObjectProvider<List<MarkFinder>> provider) {
        List<MarkFinder> markFinders = provider.getIfAvailable();
        if (!CollectionUtils.isEmpty(markFinders)) {
            return new FeignRequestMarkEnhancer(markFinders.toArray(new MarkFinder[0]));
        }
        return new FeignRequestMarkEnhancer();
    }

    @Bean
    @ConditionalOnProperty
    public SpringHttpRequestMarkEnhancer springHttpRequestMarkEnhancer(ObjectProvider<List<MarkFinder>> provider) {
        List<MarkFinder> markFinders = provider.getIfAvailable();
        if (!CollectionUtils.isEmpty(markFinders)) {
            return new SpringHttpRequestMarkEnhancer(markFinders.toArray(new MarkFinder[0]));
        }
        return new SpringHttpRequestMarkEnhancer();
    }
}
