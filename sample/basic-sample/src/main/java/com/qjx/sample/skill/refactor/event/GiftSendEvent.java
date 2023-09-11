package com.qjx.sample.skill.refactor.event;

/**
 * <Description>
 *
 * @author qinjiaxing on 2023/9/11
 * @author <others>
 */
public class GiftSendEvent extends BaseEvent {

    private String giftId;

    public GiftSendEvent(Object source, String giftId) {
        super(source);
        this.giftId = giftId;
    }

    public GiftSendEvent(String giftId) {
        this.giftId = giftId;
    }
}
