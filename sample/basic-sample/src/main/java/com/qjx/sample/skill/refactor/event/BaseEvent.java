package com.qjx.sample.skill.refactor.event;

import org.springframework.context.ApplicationEvent;

/**
 * 事件对象
 *
 * @author qinjiaxing on 2023/9/11
 * @author <others>
 */
public abstract class BaseEvent extends ApplicationEvent {

    public BaseEvent(Object source) {
        super(source);
    }

    public BaseEvent() {
        this("");
    }
}
