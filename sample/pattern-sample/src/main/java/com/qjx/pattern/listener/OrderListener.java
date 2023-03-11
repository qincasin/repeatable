package com.qjx.pattern.listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;

/**
 * <Description>
 *
 * @author qinjiaxing on 2023/3/11
 * @author <others>
 */
enum ServiceStatus {
    ONLINE,
    OFFLINE
}

// 事件类
@Getter
class ServiceChangeEvent {

    private String serviceName;
    private ServiceStatus status;

    public ServiceChangeEvent(String serviceName, ServiceStatus status) {
        this.serviceName = serviceName;
        this.status = status;
    }
}

// 定义观察者接口
interface ServiceChangeListener {

    void onServiceChange(ServiceChangeEvent event);
}

// 定义被观察者类
class ServiceRegistry {

    private Map<String, ServiceStatus> services = new HashMap<>();
    private List<ServiceChangeListener> listeners = new ArrayList<>();

    public void register(String serviceName) {
        services.put(serviceName, ServiceStatus.ONLINE);
        notifyListeners(new ServiceChangeEvent(serviceName, ServiceStatus.ONLINE));
    }

    public void unregister(String serviceName) {
        services.put(serviceName, ServiceStatus.OFFLINE);
        notifyListeners(new ServiceChangeEvent(serviceName, ServiceStatus.OFFLINE));
    }

    public void subscribe(ServiceChangeListener listener) {
        listeners.add(listener);
    }

    public void unsubscribe(ServiceChangeListener listener) {
        listeners.remove(listener);
    }

    private void notifyListeners(ServiceChangeEvent event) {
        for (ServiceChangeListener listener : listeners) {
            listener.onServiceChange(event);
        }
    }
}

public class OrderListener {

    public static void main(String[] args) {
        ServiceRegistry registry = new ServiceRegistry();
        ServiceChangeListener listener1 = event -> {
            System.out.println(String.format("这是第一个观察者需要做的事情 ： %s is %s", event.getServiceName(), event.getStatus()));
        };
        ServiceChangeListener listener2 = event2 -> {
            System.out.println(String.format("这是第二个观察者需要做的事情 ： Service %s is %s", event2.getServiceName(), event2.getStatus()));
        };
        // 注册观察者
        registry.subscribe(listener1);
        registry.subscribe(listener2);
        registry.register("serviceA");
        registry.unregister("serviceA");
        System.out.println("--------------------");
        registry.unsubscribe(listener2);
        // registry.register("serviceA");
        // registry.unregister("serviceA");
        registry.unregister("serviceB");


    }

}
