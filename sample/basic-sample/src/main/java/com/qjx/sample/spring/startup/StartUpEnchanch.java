package com.qjx.sample.spring.startup;

import com.qjx.sample.spring.startup.SmartTest.SmartTestBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * <Description>
 *
 * @author qinjiaxing on 2023/5/18
 * @author <others>
 */
@Component
public class StartUpEnchanch implements CommandLineRunner , ApplicationContextAware {
    private ApplicationContext applicationContext;
    // @Autowired
    // private SmartTest smartTest;

    @Override
    public void run(String... args) throws Exception {
        SmartTest bean1 = applicationContext.getBean(SmartTest.class);

        bean1.afterSingletonsInstantiated();

        SmartTestBean bean = applicationContext.getBean(SmartTestBean.class);
        System.out.println(bean.getName());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
