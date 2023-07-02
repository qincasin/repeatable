package com.qjx.sample.spring.importaware;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * <Description>
 *
 * @author qinjiaxing on 2023/2/20
 * @author <others>
 */
@EnableProxy
public class ImportAwareTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ImportAwareTest.class);
        System.setProperty("aop.proxy.enable", "true");
        ProxyMode bean = applicationContext.getBean(ProxyMode.class);
        System.out.println(bean);
        applicationContext.close();
    }

}
