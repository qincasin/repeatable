package com.qjx.sample.invoke;

import com.qjx.sample.invoke.InvokeMetaDataConfigProperties.GatewayExt;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * <Description>
 *
 * @author qinjiaxing on 2023/4/23
 * @author <others>
 */
@Configuration
public class InvokeConfiguration {

    public static final String USKD_INVOCKE_PREFIX = "usdk.invocation";

    @Bean
    @ConditionalOnMissingBean
    @ConfigurationProperties(prefix = USKD_INVOCKE_PREFIX)
    public InvokePolicyConfigProperties invokePolicy(Environment environment) {
        InvokePolicyConfigProperties properties = new InvokePolicyConfigProperties();
        properties.setPolicyType(environment.getProperty(USKD_INVOCKE_PREFIX + ".policy", ""));
        return properties;
    }

    @Bean
    @ConditionalOnMissingBean
    @ConfigurationProperties(prefix = USKD_INVOCKE_PREFIX + ".data")
    public InvokeMetaDataConfigProperties invokeData(Environment environment) {
        InvokeMetaDataConfigProperties properties = new InvokeMetaDataConfigProperties();
        InvokeMetaDataConfigProperties.GatewayData gateway = properties.getGateway();
        gateway.setTargetUrl(environment.getProperty(USKD_INVOCKE_PREFIX + ".data.gateway.target", ""));
        GatewayExt ext = gateway.getExt();
        //全局设置host url
        ext.setHostUrl(environment.getProperty(USKD_INVOCKE_PREFIX + ".data.gateway.ext.host", ""));
        return properties;
    }


}
