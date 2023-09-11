package com.qjx.sample.traffic.limit;

/**
 * 计数限流
 *
 * @author qinjiaxing on 2023/7/2
 * @author <others>
 */
public class CounterLimiter implements TrafficLimiter {

    private long timeStamp = System.currentTimeMillis();
    /**
     * 请求次数
     */
    private int reqCount;
    /**
     * 每秒限流的最大请求数
     */
    private int limitNum = 100;
    /**
     * 时间窗口时长 单位ms
     */
    private long interval = 1000L;

    @Override
    public synchronized boolean limit() {
        long now = System.currentTimeMillis();
        if (now < timeStamp + interval) {
            // 在当前时间窗口内
            // 判断当前时间窗口请求次数 + 1 是否超过每秒限流的最大请求以
            if (reqCount + 1 > limitNum) {
                return true;
            }
            reqCount++;
        } else {
            timeStamp = now;
            // 重置计数器
            reqCount = 1;
        }
        return false;
    }
}
