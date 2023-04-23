package com.qjx.sample.tool.learn.concurrent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <Description>
 * <p>
 * 通过apollo的ApolloConfigChangeListener注解，可以监听配置参数的变化。
 * 如果test.canal.enable开关配置的true，则调用canalService类的start方法开启canal数据同步功能。如果开关配置的false，则调用canalService类的stop方法，自动停止canal数据同步功能。
 *
 * @author qinjiaxing on 2023/4/23
 * @author <others>
 */
@Service
public class CanalService {

    private volatile boolean running = false;
    @Autowired
    private CanalConnect canalConnect;
    private Thread thread;

    private void handle() {
        // 连接cannel
        while (running) {
            // 业务处理
        }
    }

    public void start() {
        thread = new Thread(this::handle, "thread-name-");
        running = true;
        thread.start();
    }

    public void stop() {
        if (!running) {
            return;
        }
        running = false;
    }

}

@Service
class CanalConnect {

}
