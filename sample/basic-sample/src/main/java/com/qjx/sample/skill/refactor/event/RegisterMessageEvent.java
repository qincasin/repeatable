package com.qjx.sample.skill.refactor.event;

/**
 *  发送消息的事件
 *
 * @author qinjiaxing on 2023/9/11
 * @author <others>
 */
public class RegisterMessageEvent extends BaseEvent{
    private String msgId;

    public RegisterMessageEvent(Object source, String msgId) {
        super(source);
        this.msgId = msgId;
    }

    public RegisterMessageEvent(String msgId) {
        this.msgId = msgId;
    }

}
