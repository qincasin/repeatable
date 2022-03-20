package com.qjx.repeatable;

import com.qjx.external.spring.EnableTestImport;
import com.qjx.external.spring.EnableTestImport2;
import com.qjx.external.spring.TestImportA;
import com.qjx.external.spring.TestImportB;
import com.qjx.external.spring.TestImportConfig;
import com.qjx.repeatable.spring.service.HelloService;
import com.qjx.repeatable.utils.SpringContextHolder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
//第一种
//@Import({TestImportA.class,TestImportB.class})
//第二种import方式
//@Import(TestImportConfig.class)
//第三种import方式
@EnableTestImport
// 第四种import方式
@EnableTestImport2
public class RepeatableApplication {

	public static void main(String[] args) {
//        SpringApplication springApplication = new SpringApplication(RepeatableApplication.class);
//        springApplication.addInitializers(new TestApplicationContextInitializer());
        ConfigurableApplicationContext run = SpringApplication.run(RepeatableApplication.class, args);
        //        HelloService bean = SpringContextHolder.getBean(HelloService.class);
//        bean.test();
        TestImportA bean = (TestImportA)run.getBean("testImportA");
        bean.test();
//        TestImportB beanB = run.getBean(TestImportB.class);
//        beanB.test();

    }

}
