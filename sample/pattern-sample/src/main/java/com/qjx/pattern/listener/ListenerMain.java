package com.qjx.pattern.listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <Description>
 *
 * @author qinjiaxing on 2023/3/11
 * @author <others>
 */
// 目标类，它知道它的观察者，并提供注册和删除观察者的方法，以及通知观察者的方法。
interface Subject {

    // 注册
    void registerObserver(Observer o);

    // 移除
    void removeObserver(Observer o);

    // 通知
    void notifyObservers(String event);
}

// 观察者接口，它定义了当目标对象状态发生改变时所需的更新接口。
interface Observer {

    // 更新
    void update(String event);
}

// 具体目标类，它存储对观察者对象的引用，当其状态发生改变时，通知所有注册的观察者。
class ConcreteSubject implements Subject {

    private List<Observer> observerList = new ArrayList<>();
    private Map<String, String> data = new HashMap<>();

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
        // notifyObservers("dataChanged");
    }

    @Override
    public void registerObserver(Observer o) {
        observerList.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observerList.remove(o);
    }

    @Override
    public void notifyObservers(String event) {
        for (Observer observer : observerList) {
            observer.update(event);
        }
    }
}

// 具体观察者类，实现观察者接口中的更新方法，以便在目标状态发生改变时更新自身状态。
class ConcreteObserver implements Observer {

    private String name;


    public ConcreteObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(String event) {
        System.out.println(name + " received event: " + event);
    }
}

public class ListenerMain {
    // private
    public static void main(String[] args) {
        ConcreteSubject subject = new ConcreteSubject();
        ConcreteObserver A = new ConcreteObserver("A");
        ConcreteObserver B = new ConcreteObserver("B");
        subject.registerObserver(A);
        subject.registerObserver(B);
        Map<String,String> map =new HashMap<>();
        map.put("key","value");
        subject.setData(map);
        subject.notifyObservers(map.toString());

    }

}

