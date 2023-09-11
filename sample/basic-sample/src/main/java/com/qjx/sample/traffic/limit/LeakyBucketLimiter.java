package com.qjx.sample.traffic.limit;

import java.util.Map;
import lombok.extern.slf4j.Slf4j;

/**
 * 漏斗算法
 *
 * @author qinjiaxing on 2023/7/2
 * @author <others>
 */
@Slf4j
public class LeakyBucketLimiter implements TrafficLimiter {

    private long timestamp = System.currentTimeMillis();
    // 桶的容量
    private long capacity = 100;
    // 水桶出的速率（每秒系统能处理的请求数）
    private long rate = 10;
    // 当前水量（当前累积请求数）
    private long water = 20;

    @Override
    public boolean limit() {
        long now = System.currentTimeMillis();
        water = Math.max(0, water - (now - timestamp / 1000) * rate);
        timestamp = now;
        if (water + 1 <= capacity) {
            // 水还未满，加水
            water++;
            log.info("加水 ：{}" + water);
            return false;
        } else {
            // 水满，拒绝加水
            log.info("水满，拒绝加水 ：{}" + water);
            return true;
        }
    }
}
