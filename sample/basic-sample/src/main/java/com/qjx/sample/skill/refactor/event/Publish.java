package com.qjx.sample.skill.refactor.event;

import java.util.concurrent.ThreadPoolExecutor;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationContext;

/**
 * 发布
 *
 * @author qinjiaxing on 2023/9/11
 * @author <others>
 */
@AllArgsConstructor
public class Publish {

    private final ApplicationContext applicationContext;
    private final ThreadPoolExecutor threadPoolExecutor;

    /**
     * 同步阻塞发布
     *
     * @param event
     */
    void publishEvent(BaseEvent event) {
        applicationContext.publishEvent(event);
    }

    /**
     * 异步发布（异步非阻塞）
     *
     * @param event
     */
    void asyncPublishEvent(BaseEvent event) {
        threadPoolExecutor.execute(() -> applicationContext.publishEvent(event));
    }
}
