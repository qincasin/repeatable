package com.qjx.repeatable.pattern.listener;

/**
 * java的事件监听机制可概括为3点：
 *
 * java的事件监听机制涉及到 事件源，事件监听器，事件对象 三个组件,监听器一般是接口，用来约定调用方式
 * 当事件源对象上发生操作时，它将会调用事件监听器的一个方法，并在调用该方法时传递事件对象过去
 * 事件监听器实现类,通常是由开发人员编写，开发人员通过事件对象拿到事件源，从而对事件源上的操作进行处理
 *
 * 监听器接口
 */
public interface EventListener extends java.util.EventListener {
    //事件处理
    public void handleEvent(EventObject event);


}
