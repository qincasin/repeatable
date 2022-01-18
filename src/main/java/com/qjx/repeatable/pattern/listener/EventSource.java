package com.qjx.repeatable.pattern.listener;

import java.util.Vector;

/**
 * 事件源是事件对象的入口，包含监听器的注册、撤销、通知
 */
public class EventSource {
    private Vector<EventListener> eventListeners = new Vector<>();

    //监听器的注册
    public void addEventListener(EventListener eventListener) {
        eventListeners.add(eventListener);
    }

    //撤销
    public void removeEventListener(EventListener eventListener) {
        eventListeners.remove(eventListener);
    }

    //接收外部事件
    public void notifyListenerEvents(EventObject event) {
        for (EventListener eventListener : eventListeners) {
            eventListener.handleEvent(event);
        }
    }

    //特定的 关注方法和事件触发
    public void onCloseWindows(EventListener eventListener) {
        System.out.println("关注关闭窗口");
    }

    public void doCloseWindows() {
        this.notifyListenerEvents(new EventObject("closeWindows"));
    }


}
