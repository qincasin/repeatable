package com.qjx.repeatable.pattern.listener;

public class Main {
    public static void main(String[] args) {
        EventSource eventSource = new EventSource();
        eventSource.addEventListener(event -> {
            event.doEvent();
            if (event.getSource().equals("closeWindows")) {
                System.out.println("do Close ...");
            }
        });

        eventSource.notifyListenerEvents(new EventObject("closeWindows"));


        EventSource windows = new EventSource();

        windows.addEventListener((eventObject) -> {
            eventObject.doEvent();
            if (eventObject.getSource().equals("closeWindows")) {
                System.out.println("通过onCloseWindows来关注关闭窗口事件：并执行成功。 closeWindows");
            }
        });

        windows.doCloseWindows();

    }
}
