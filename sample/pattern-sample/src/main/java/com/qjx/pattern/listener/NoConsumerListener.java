package com.qjx.pattern.listener;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 * <Description>
 *
 * @author qinjiaxing on 2023/3/11
 * @author <others>
 */
interface EventListener {

    void onEvent(Event event);
}

@Data
class Event {

    private String msg;
}

class EventPublisher_ {

    public List<EventListener> listenerList = new ArrayList<>();

    public void addListener(EventListener listener) {
        listenerList.add(listener);
    }

    public void publish(String msg) {
        Event event = new Event();
        event.setMsg(msg);
        for (EventListener listener : listenerList) {
            listener.onEvent(event);
        }
    }
}

class CustomEventListener implements EventListener {

    @Override
    public void onEvent(Event event) {
        System.out.println("event " + event.getMsg());
    }
}

class Custom2EventListener implements EventListener {

    @Override
    public void onEvent(Event event) {
        System.out.println("event 2 " + event.getMsg());
    }
}


public class NoConsumerListener {

    public static void main(String[] args) {
        EventPublisher_ eventPublisher = new EventPublisher_();
        eventPublisher.addListener(new Custom2EventListener());
        eventPublisher.addListener(new CustomEventListener());
        eventPublisher.publish("hello ");
    }

}
