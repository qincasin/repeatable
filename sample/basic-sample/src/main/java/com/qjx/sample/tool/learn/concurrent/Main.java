package com.qjx.sample.tool.learn.concurrent;

import lombok.extern.slf4j.Slf4j;

/**
 * <Description>
 * 参考 https://mp.weixin.qq.com/s/C59KZVZhcgnzu3DYON8gQw
 *
 * @author qinjiaxing on 2023/4/23
 * @author <others>
 */
@Slf4j
public class Main {
    // 1. 简单定时任务

    /**
     * 使用场景：比如项目中有时需要每隔5分钟去下载某个文件，或者每隔10分钟去读取模板文件生成静态html页面等等，一些简单的周期性任务场景。
     * 使用Thread类做定时任务的优缺点：
     * 优点：这种定时任务非常简单，学习成本低，容易入手，对于那些简单的周期性任务，是个不错的选择。
     * 缺点：不支持指定某个时间点执行任务，不支持延迟执行等操作，功能过于单一，无法应对一些较为复杂的场景。
     */
    public static void init() {
        new Thread(() -> {
            while (true) {
                try {
                    System.out.println("下载文件");
                    Thread.sleep(1000 * 60 * 5);
                } catch (Exception e) {
                    log.error("e:  ", e);
                }
            }
        }).start();
    }
    // 2. 监听器


}
