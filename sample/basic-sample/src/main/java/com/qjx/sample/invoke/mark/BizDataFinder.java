package com.qjx.sample.invoke.mark;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

/**
 * <Description>
 *
 * @author qinjiaxing on 2023/4/23
 * @author <others>
 */
public class BizDataFinder implements MarkFinder, ApplicationContextAware, EnvironmentAware {

    private Environment environment;
    private ApplicationContext applicationContext;

    @Override
    public Map<String, String> find(String... filterKeys) {
        Map<String, String> map = new HashMap<>();
        return map;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
