package com.qjx.sample.skill.refactor.event;

import org.springframework.stereotype.Component;

/**
 * <Description>
 *
 * @author qinjiaxing on 2023/9/11
 * @author <others>
 */
@Component
public class RegisterMessageListenerImpl implements BaseEventListener<RegisterMessageEvent> {

    @Override
    public void handler(RegisterMessageEvent event) {
        System.out.println("发送消息的事件");
    }

    @Override
    public void handleException(Throwable e) {
        System.out.println("发送消息的事件异常");
    }

}
