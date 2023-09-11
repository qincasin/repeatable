package com.qjx.sample.skill.refactor.event;

import java.util.concurrent.ThreadPoolExecutor;
import org.springframework.context.ApplicationContext;

/**
 * <Description>
 *
 * @author qinjiaxing on 2023/9/11
 * @author <others>
 */
public class Main {

    private static ApplicationContext applicationContext;
    private static ThreadPoolExecutor threadPoolExecutor;


    public static void main(String[] args) {
        Publish publish = new Publish(applicationContext, threadPoolExecutor);
        // 1. 注册完成
        registerUser();
        // 2. 发送事件
        publish.publishEvent(new RegisterMessageEvent("哈哈哈"));
    }

    private static void registerUser() {
        // 注册逻辑
    }

}
