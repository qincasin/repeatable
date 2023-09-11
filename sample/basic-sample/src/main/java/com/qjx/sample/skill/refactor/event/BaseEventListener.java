package com.qjx.sample.skill.refactor.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * 定义Listener监听者（观察者）
 *
 * @author qinjiaxing on 2023/9/11
 * @author <others>
 */
public interface BaseEventListener<T extends BaseEvent> extends ApplicationListener<T> {

    /**
     * 发布事件
     *
     * @param event
     */
    default void onApplicationEvent(T event) {
        try {
            if (support()) {
                handler(event);
            }
        } catch (Throwable e) {
            // 异常处理
            handleException(e);
        }
    }


    // 默认执行
    default boolean support() {
        return true;
    }

    /**
     * 具体事件需要做哪些事情
     *
     * @param event
     */
    void handler(T event);

    /**
     * 异常处理
     *
     * @param e
     */
    void handleException(Throwable e);

}
