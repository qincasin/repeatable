package com.qjx.pattern.listener;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * <Description>
 *
 * @author qinjiaxing on 2023/3/11
 * @author <others>
 */
class EventPublisher {

    List<Consumer<String>> listeners = new ArrayList<>();

    public void addListen(Consumer<String> listener) {
        listeners.add(listener);
    }

    public void publish(String msg) {
        for (Consumer<String> listener : listeners) {
            listener.accept(msg);
        }
    }
}

public class ConsumerListener {

    public static void main(String[] args) {
        EventPublisher eventPublisher = new EventPublisher();
        eventPublisher.addListen((message) -> System.out.println("Listener 1 received message: " + message));
        eventPublisher.addListen((message) -> System.out.println("Listener 2 received message: " + message));
        eventPublisher.publish("hello");
    }

}
