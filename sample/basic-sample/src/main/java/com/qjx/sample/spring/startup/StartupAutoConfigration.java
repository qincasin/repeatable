package com.qjx.sample.spring.startup;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * <Description>
 *
 * @author qinjiaxing on 2023/5/18
 * @author <others>
 */
@Configuration
@Import({TestImport.class,SmartTest.class})
public class StartupAutoConfigration {

}
