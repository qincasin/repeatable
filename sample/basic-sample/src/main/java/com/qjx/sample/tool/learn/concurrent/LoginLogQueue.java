package com.qjx.sample.tool.learn.concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * <Description>
 *
 * @author qinjiaxing on 2023/4/23
 * @author <others>
 */
@Component
@Slf4j
public class LoginLogQueue {

    private BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>(10000);

    // 生成消息
    public boolean push(String log) {
        return this.blockingQueue.add(log);
    }

    // 消费嘻嘻
    public String take() {
        String log = null;
        try {
            log = this.blockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return log;
    }

}

@Service
class LoginConsume {

    @Autowired
    public LoginLogQueue loginLogQueue;

    @PostConstruct
    public void init() {
        new Thread(() -> {
            while (true) {
                String take = loginLogQueue.take();
                System.out.println(take);
                // 写入到数据库
            }
        }).start();
    }
}
